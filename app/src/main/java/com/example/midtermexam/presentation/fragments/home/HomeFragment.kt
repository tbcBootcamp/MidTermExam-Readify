package com.example.midtermexam.presentation.fragments.home

import android.content.Intent
import android.net.Uri
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midtermexam.data.global.model.BookItemDto
import com.example.midtermexam.databinding.FragmentHomeBinding
import com.example.midtermexam.presentation.adapters.BooksPagingAdapter
import com.example.midtermexam.presentation.adapters.ContentsLoadStateAdapter
import com.example.midtermexam.presentation.base.BaseFragment
import com.example.midtermexam.data.global.util.isOnline
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var pagingAdapter: BooksPagingAdapter
    override fun setUp() {
        setupAdapter()
    }

    override fun listeners() {
        if (!isOnline(requireContext())) {
            binding.noDataLottie.isVisible = true
        }
        binding.searchQuery.doOnTextChanged { text, start, before, count ->
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.requestSearch(text.toString(), requireContext()).collect {
                        pagingAdapter.submitData(it)
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.requestSearch("", requireContext()).collect {
                    pagingAdapter.submitData(it)
                }
            }
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    binding.booksLoadingView.isVisible = it.isLoading
                }
            }
        }
    }

    private fun setupAdapter() {
        pagingAdapter = BooksPagingAdapter()
        pagingAdapter.withLoadStateHeaderAndFooter(
            header = ContentsLoadStateAdapter { pagingAdapter.retry() },
            footer = ContentsLoadStateAdapter { pagingAdapter.retry() }
        )
        binding.rvSearch.adapter = pagingAdapter
        binding.rvSearch.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val dividerItemDecoration = DividerItemDecoration(
            binding.rvSearch.context,
            DividerItemDecoration.VERTICAL
        )
        binding.rvSearch.addItemDecoration(dividerItemDecoration)

        pagingAdapter.setStarDelete {
            viewModel.deleteStarFromDb(it)
        }
        pagingAdapter.setStarInsert {
            viewModel.insertStarToDb(it)
        }
        pagingAdapter.setLinkInfo {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        }

    }
}
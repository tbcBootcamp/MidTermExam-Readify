package com.example.midtermexam.presentation.fragments.favourites

import android.content.Intent
import android.net.Uri
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midtermexam.databinding.FragmentFavouritesBinding
import com.example.midtermexam.presentation.adapters.FavoritesAdapter
import com.example.midtermexam.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment :
    BaseFragment<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate) {
    private val viewModel: FavouritesViewModel by viewModels()
    private lateinit var adapter: FavoritesAdapter

    override fun listeners() {}

    override fun setUp() {
        setupAdapter()
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getFavorites().collect {
                    binding.noDataLottie.isVisible = it.isEmpty()
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun setupAdapter() {
        adapter = FavoritesAdapter()
        binding.rvLocal.adapter = adapter
        binding.rvLocal.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adapter.setStarDelete {
            viewModel.deleteStarFromDb(it)
        }
        adapter.setStarInsert {
            viewModel.insertStarToDb(it)
        }
        adapter.setLinkInfo {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        }

    }

}
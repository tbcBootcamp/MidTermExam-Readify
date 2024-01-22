package com.example.midtermexam.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermexam.databinding.PagingLoadingItemBinding

class ContentsLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ContentsLoadStateAdapter.PassengersLoadStateViewHolder>() {

    inner class PassengersLoadStateViewHolder(
        private val binding: PagingLoadingItemBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.textError.text = loadState.error.localizedMessage
            }
            binding.progressbar.isVisible = (loadState is LoadState.Loading)
            binding.buttonRetry.isVisible = (loadState is LoadState.Error)
            binding.textError.isVisible = (loadState is LoadState.Error)
            binding.buttonRetry.setOnClickListener {
                retry()
            }
        }
    }

    override fun onBindViewHolder(
        holder: PassengersLoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = PassengersLoadStateViewHolder(
        PagingLoadingItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
        retry
    )
}
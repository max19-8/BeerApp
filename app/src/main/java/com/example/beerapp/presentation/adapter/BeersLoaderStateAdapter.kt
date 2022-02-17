package com.example.beerapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beerapp.R
import com.example.beerapp.databinding.ItemErrorBinding

class BeersLoaderStateAdapter(
        private val retry: () -> Unit
    ) : LoadStateAdapter<BeersLoaderStateAdapter.LoadStateViewHolder>() {

        inner class LoadStateViewHolder(
            private val binding: ItemErrorBinding,
            private val retry: () -> Unit
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(loadState: LoadState) {
                if (loadState is LoadState.Error) {
                    binding.textViewError.text = itemView.context.getString(R.string.error_download_text)
                }
                binding.progressbar.visible(loadState is LoadState.Loading)
                binding.buttonRetry.visible(loadState is LoadState.Error)
                binding.textViewError.visible(loadState is LoadState.Error)
                binding.buttonRetry.setOnClickListener {
                    retry()
                }

                binding.progressbar.visibility = View.VISIBLE
            }
        }

        override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
            holder.bind(loadState)
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            loadState: LoadState
        ) = LoadStateViewHolder(
            ItemErrorBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )
    }

    fun View.visible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }
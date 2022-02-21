package com.example.beerapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beerapp.R
import com.example.beerapp.databinding.BeerItemBinding
import com.example.beerapp.presentation.model.BeerPresentationModelItem

class BeerListAdapter(private val onBeerClickListener: OnBeerClickListener,
                      private val isFavoriteClickListener: IsFavoriteClickListener,
                      private val checkIsFavoriteListener: CheckIsFavoriteListener) :
    PagingDataAdapter<BeerPresentationModelItem, BeerListAdapter.BeerViewHolder>(Diff()) {

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.binds(currentItem)
            holder.isFavorite(isFavoriteClickListener,currentItem)
            holder.itemView.setOnClickListener {
                onBeerClickListener.onBeerClick(
                    currentItem
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val binding = BeerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerViewHolder(binding)
    }
    inner class BeerViewHolder(private val binding: BeerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binds(beer: BeerPresentationModelItem) {
            binding.apply {
                Glide.with(itemView)
                    .load(beer.imageUrl)
                    .fitCenter()
                    .error(R.drawable.placeholder)
                    .into(beerImage)
                beerNameTextView.text = beer.name
                strengthBeerTextView.text = itemView.context.getString(
                    R.string.alcohol_content_text,
                    beer.strengthDrinks.toString()
                )
            }
            checkIsFavoriteListener.checkIsFavorite(beer,binding.likeButton)
        }

        fun isFavorite(isFavoriteClickListener: IsFavoriteClickListener,beer:BeerPresentationModelItem){
            binding.likeButton.setOnCheckedChangeListener {_, isChecked ->
                isFavoriteClickListener.changeIsFavorite(beer,!isChecked)
            }
        }
    }

    class Diff : DiffUtil.ItemCallback<BeerPresentationModelItem>() {
        override fun areItemsTheSame(
            oldItem: BeerPresentationModelItem,
            newItem: BeerPresentationModelItem
        ): Boolean =
            oldItem.id == newItem.id
        override fun areContentsTheSame(
            oldItem: BeerPresentationModelItem,
            newItem: BeerPresentationModelItem
        ): Boolean =
            oldItem == newItem
    }
}
package com.example.beerapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beerapp.R
import com.example.beerapp.data.model.BeerRemoteModelItem
import com.example.beerapp.databinding.BeerItemBinding
import com.example.beerapp.presentation.model.BeerPresentationModelItem

class BeerListAdapter : PagingDataAdapter<BeerPresentationModelItem,BeerListAdapter.BeerViewHolder>(Diff()) {
    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem!= null){
            holder.binds(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val binding = BeerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BeerViewHolder(binding)
    }

    class BeerViewHolder(private val binding:BeerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun binds(beer: BeerPresentationModelItem){
            binding.apply {
                Glide.with(itemView)
                    .load(beer.imageUrl)
                    .fitCenter()
                    .error(R.drawable.placeholder)
                    .into(beerImage)
                beerNameTextView.text = beer.name
                strengthBeerTextView.text =  itemView.context.getString(R.string.alcohol_content_text, beer.strengthDrinks.toString())
            }
        }
    }

    class Diff : DiffUtil.ItemCallback<BeerPresentationModelItem>(){
        override fun areItemsTheSame(oldItem: BeerPresentationModelItem, newItem: BeerPresentationModelItem): Boolean  =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BeerPresentationModelItem, newItem:BeerPresentationModelItem): Boolean =
            oldItem == newItem
    }
}
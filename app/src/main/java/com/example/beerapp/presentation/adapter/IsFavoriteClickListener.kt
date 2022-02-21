package com.example.beerapp.presentation.adapter

import com.example.beerapp.presentation.model.BeerPresentationModelItem

interface IsFavoriteClickListener {
    fun addDeleteFavorite(beerPresentationModelItem: BeerPresentationModelItem, isFavorite:Boolean)
}
package com.example.beerapp.presentation.adapter

import com.example.beerapp.presentation.model.BeerPresentationModelItem

interface IsFavoriteClickListener {
    fun changeIsFavorite(beerPresentationModelItem: BeerPresentationModelItem, isFavorite:Boolean)
}
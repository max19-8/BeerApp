package com.example.beerapp.presentation.adapter

import com.example.beerapp.presentation.model.BeerPresentationModelItem

interface OnBeerClickListener {
    fun onBeerClick(beer: BeerPresentationModelItem)
}
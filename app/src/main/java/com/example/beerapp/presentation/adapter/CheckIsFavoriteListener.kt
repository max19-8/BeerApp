package com.example.beerapp.presentation.adapter

import android.widget.ToggleButton
import com.example.beerapp.presentation.model.BeerPresentationModelItem

interface CheckIsFavoriteListener {
    fun checkIsFavorite(beerPresentationModelItem: BeerPresentationModelItem,btn: ToggleButton)
}
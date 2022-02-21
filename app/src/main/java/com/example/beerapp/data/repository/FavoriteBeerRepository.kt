package com.example.beerapp.data.repository

import com.example.beerapp.presentation.model.BeerPresentationModelItem

interface FavoriteBeerRepository {
    suspend fun addToFavorite(beerPresentationModelItem: BeerPresentationModelItem)
    suspend fun removeFromFavorite(beerPresentationModelItem: BeerPresentationModelItem)
    suspend fun isFavorite(id: Int): Boolean

}
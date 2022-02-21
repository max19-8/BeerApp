package com.example.beerapp.domain

import com.example.beerapp.data.repository.FavoriteBeerRepositoryImpl
import com.example.beerapp.presentation.model.BeerPresentationModelItem

class FavoriteBeerUseCase(private val favoriteBeerRepositoryImpl: FavoriteBeerRepositoryImpl) {

    suspend fun addDeleteFavorite(
        beerPresentationModelItem: BeerPresentationModelItem,
        isFavorite: Boolean
    ) =
        if (isFavorite)
            favoriteBeerRepositoryImpl.removeFromFavorite(beerPresentationModelItem)
        else
            favoriteBeerRepositoryImpl.addToFavorite(beerPresentationModelItem)

    suspend fun isFavorite(id: Int) =
        favoriteBeerRepositoryImpl.isFavorite(id)
}
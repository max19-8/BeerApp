package com.example.beerapp.data.repository

import com.example.beerapp.database.FavoriteBeerDao
import com.example.beerapp.presentation.model.BeerPresentationModelItem

class FavoriteBeerRepositoryImpl(private val dao: FavoriteBeerDao):FavoriteBeerRepository {
    override suspend fun addToFavorite(beerPresentationModelItem: BeerPresentationModelItem) =
        dao.addToFavorite(beerPresentationModelItem)

    override suspend fun removeFromFavorite(beerPresentationModelItem: BeerPresentationModelItem) =
        dao.deleteFavorite(beerPresentationModelItem)

    override suspend fun isFavorite(id: Int): Boolean =
        dao.isFavorite(id)
}

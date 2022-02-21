package com.example.beerapp.database

import androidx.room.*
import com.example.beerapp.presentation.model.BeerPresentationModelItem

@Dao
interface FavoriteBeerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(beerPresentationModelItem: BeerPresentationModelItem)

    @Delete
    suspend fun deleteFavorite(beerPresentationModelItem: BeerPresentationModelItem)

    @Query("SELECT EXISTS (SELECT 1 FROM favorite_beer WHERE id=:id)")
    suspend fun isFavorite(id: Int): Boolean
}
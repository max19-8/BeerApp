package com.example.beerapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.beerapp.presentation.model.BeerPresentationModelItem


@Database(entities = [BeerPresentationModelItem::class], version = 1)
abstract class FavoriteBeerDatabase : RoomDatabase() {
    abstract fun favoriteBeersDao(): FavoriteBeerDao
}
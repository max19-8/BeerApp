package com.example.beerapp.database

import android.content.Context
import androidx.room.Room

interface CacheDataSource : FavoriteBeerDataSource {

    abstract class Abstract(context: Context, databaseName: String) : CacheDataSource {

        private val database = Room.databaseBuilder(
            context,
            FavoriteBeerDatabase::class.java,
            databaseName
        ).build()

        override fun getBeerDao(): FavoriteBeerDao = database.favoriteBeersDao()
    }

    class Base(context: Context) : Abstract(context, "BEERS_DB")
}

// FIXME: переписал реализацию бд, теперь ее можно подменить и использовать в  юнит тестах вот так:
/*
    class Test(context:Context):CacheDataSource{
        override fun getBeerDao(): FavoriteBeerDao {
            return object : FavoriteBeerDao{
                override suspend fun addToFavorite(beerPresentationModelItem: BeerPresentationModelItem) {
                        BeerPresentationModelItem(1,"fsdf","fsdf","fsdf","fsdf","fsdf",2.2,2.2,false)
                }
                override suspend fun deleteFavorite(beerPresentationModelItem: BeerPresentationModelItem) {
                    //do nothing
                }

                override suspend fun isFavorite(id: Int): Boolean {
                    return true
                }
            }
        }
    }
 */
interface FavoriteBeerDataSource {
    fun getBeerDao(): FavoriteBeerDao
}
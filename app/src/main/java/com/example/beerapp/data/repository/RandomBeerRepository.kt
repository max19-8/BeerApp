package com.example.beerapp.data.repository

import com.example.beerapp.data.model.BeerRemoteModelItem

interface RandomBeerRepository {
    suspend fun getRandomBeer(): List<BeerRemoteModelItem>
}
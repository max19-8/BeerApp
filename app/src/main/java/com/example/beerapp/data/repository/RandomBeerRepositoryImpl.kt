package com.example.beerapp.data.repository

import com.example.beerapp.data.api.ApiService
import com.example.beerapp.data.model.BeerRemoteModelItem

class RandomBeerRepositoryImpl(private val apiService: ApiService) : RandomBeerRepository {
    override suspend fun getRandomBeer(): List<BeerRemoteModelItem> = apiService.getRandomBeer()
}
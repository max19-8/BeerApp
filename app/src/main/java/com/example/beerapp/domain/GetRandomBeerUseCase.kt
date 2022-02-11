package com.example.beerapp.domain

import com.example.beerapp.data.model.toPresentationBeer
import com.example.beerapp.data.repository.RandomBeerRepositoryImpl
import com.example.beerapp.presentation.model.BeerPresentationModelItem


class GetRandomBeerUseCase(private val randomBeerRepositoryImpl: RandomBeerRepositoryImpl) {
    suspend fun getRandomBeer(): BeerPresentationModelItem =
        randomBeerRepositoryImpl.getRandomBeer().first().toPresentationBeer()
}
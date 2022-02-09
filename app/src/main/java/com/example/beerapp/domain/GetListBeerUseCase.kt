package com.example.beerapp.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.example.beerapp.data.model.BeerRemoteModelItem
import com.example.beerapp.data.model.toPresentationBeer
import com.example.beerapp.data.repository.ListBeerRepositoryImpl
import com.example.beerapp.presentation.model.BeerPresentationModelItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class GetListBeerUseCase(private val listBeerRepositoryImpl: ListBeerRepositoryImpl) {
    fun getBeersListByPage() : Flow<PagingData<BeerPresentationModelItem>> =
        listBeerRepositoryImpl.getBeersListByPage().map { it.toPresentationBeer() }
}
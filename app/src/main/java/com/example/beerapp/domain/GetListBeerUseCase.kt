package com.example.beerapp.domain

import androidx.paging.PagingData
import com.example.beerapp.data.model.BeerRemoteModelItem
import com.example.beerapp.data.repository.ListBeerRepositoryImpl
import kotlinx.coroutines.flow.Flow

class GetListBeerUseCase(private val listBeerRepositoryImpl: ListBeerRepositoryImpl) {
    fun getBeersListByPage() : Flow<PagingData<BeerRemoteModelItem>> =
        listBeerRepositoryImpl.getBeersListByPage()
}
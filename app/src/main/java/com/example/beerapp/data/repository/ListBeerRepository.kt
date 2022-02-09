package com.example.beerapp.data.repository

import androidx.paging.PagingData
import com.example.beerapp.data.model.BeerRemoteModelItem
import kotlinx.coroutines.flow.Flow

interface ListBeerRepository {
    fun getBeersListByPage(): Flow<PagingData<BeerRemoteModelItem>>
}
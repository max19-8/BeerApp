package com.example.beerapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.beerapp.data.api.ApiService
import com.example.beerapp.data.paging.BeerPagingSource
import com.example.beerapp.data.model.BeerRemoteModelItem
import kotlinx.coroutines.flow.Flow

class ListBeerRepositoryImpl(private val apiService: ApiService):ListBeerRepository {

    override  fun getBeersListByPage(query:String) : Flow<PagingData<BeerRemoteModelItem>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { BeerPagingSource(apiService,query) }
        ).flow
}
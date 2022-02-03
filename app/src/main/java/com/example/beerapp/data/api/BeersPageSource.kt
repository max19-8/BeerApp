package com.example.beerapp.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.beerapp.data.model.BeerRemoteModelItem

private const val STARTING_PAGE_INDEX = 1

class BeersPageSource(private val service: ApiService):PagingSource<Int,BeerRemoteModelItem>() {
    override fun getRefreshKey(state: PagingState<Int, BeerRemoteModelItem>): Int? {

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerRemoteModelItem> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        val pageSize = params.loadSize

        val response = service.getBeersListByPage(pageIndex,pageSize)
         val nextKey = if (response.isEmpty){
             null
         }else{
             pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
         }
}
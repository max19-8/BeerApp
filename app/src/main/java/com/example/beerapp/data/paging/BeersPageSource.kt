package com.example.beerapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.beerapp.data.api.ApiService
import com.example.beerapp.data.model.BeerRemoteModelItem

private const val STARTING_PAGE_INDEX = 1

class BeerPagingSource(private val apiService: ApiService) :
    PagingSource<Int, BeerRemoteModelItem>() {

    override fun getRefreshKey(state: PagingState<Int, BeerRemoteModelItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerRemoteModelItem> {
        val page = params.key ?: STARTING_PAGE_INDEX
        val pageSize = params.loadSize
        return try {
            val response = apiService.getBeersListByPage(page, pageSize)
            Log.d("BeerPagingSource", "$response")
            val nextKey = if (response.size < pageSize) null else page + 1
            val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
            Log.d("BeerPagingSource", "$response")
            LoadResult.Page(
                response,
                prevKey,
                nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
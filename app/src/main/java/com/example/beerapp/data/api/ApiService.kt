package com.example.beerapp.data.api

import com.example.beerapp.data.model.BeerRemoteModelItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/beers?")
    suspend fun getBeersListByPage(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<BeerRemoteModelItem>

    @GET("v2/beers/random")
    suspend fun getRandomBeer():List<BeerRemoteModelItem>

}
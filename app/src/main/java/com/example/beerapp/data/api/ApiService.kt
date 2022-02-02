package com.example.beerapp.data.api

import com.example.beerapp.data.model.BeerRemoteModelItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/v2/beers?page={page}&per_page={per_page=}")
    fun getBeersListByPage(@Path("page")page:Int,
                           @Path("per_page")perPage:Int)
    : Observable<List<BeerRemoteModelItem>>
}
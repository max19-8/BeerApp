package com.example.beerapp.data.api

import com.example.beerapp.data.model.BeerRemoteModelList
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("/v2/beers?page=1&per_page=1")
    fun getListBeerFromPage(): Observable<BeerRemoteModelList>
}
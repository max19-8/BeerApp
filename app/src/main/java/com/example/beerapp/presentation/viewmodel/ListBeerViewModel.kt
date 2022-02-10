package com.example.beerapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.beerapp.domain.GetListBeerUseCase
import com.example.beerapp.presentation.model.BeerPresentationModelItem
import kotlinx.coroutines.flow.Flow


class ListBeerViewModel(private val getListBeerUseCase: GetListBeerUseCase):ViewModel() {

    private lateinit var beers: Flow<PagingData<BeerPresentationModelItem>>
    val getBeers: Flow<PagingData<BeerPresentationModelItem>>
        get() = beers

    init {
        setBeers()
    }
    private fun setBeers(){
        beers = getListBeerUseCase.getBeersListByPage().cachedIn(viewModelScope)
        }
    }
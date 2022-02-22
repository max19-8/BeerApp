package com.example.beerapp.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.beerapp.domain.GetListBeerUseCase
import com.example.beerapp.presentation.model.BeerPresentationModelItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

class ListBeerViewModel(private val getListBeerUseCase: GetListBeerUseCase):ViewModel() {

    private  var beers: Flow<PagingData<BeerPresentationModelItem>>
    val getBeers: Flow<PagingData<BeerPresentationModelItem>>
        get() = beers
    private val searchBy = MutableLiveData("")

    var check : Boolean = false


    init {
        beers = searchBy.asFlow().debounce(800).flatMapLatest {
            getListBeerUseCase.getBeersListByPage(it).cachedIn(viewModelScope)
        }
    }
        fun onQueryChange(value: String) {
            if (this.searchBy.value == value) return
            this.searchBy.value = value
        }
}
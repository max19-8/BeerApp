package com.example.beerapp.presentation.viewmodel

import android.widget.ToggleButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.beerapp.domain.FavoriteBeerUseCase
import com.example.beerapp.domain.GetListBeerUseCase
import com.example.beerapp.presentation.model.BeerPresentationModelItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class ListBeerViewModel(private val getListBeerUseCase: GetListBeerUseCase,
                        private val favoriteBeerUseCase: FavoriteBeerUseCase):ViewModel() {

    private  var beers: Flow<PagingData<BeerPresentationModelItem>>
    val getBeers: Flow<PagingData<BeerPresentationModelItem>>
        get() = beers
    private val searchBy = MutableLiveData("")

    init {
        beers = searchBy.asFlow().debounce(800).flatMapLatest {
            getListBeerUseCase.getBeersListByPage(it).cachedIn(viewModelScope)
        }
    }
    fun onQueryChange(value: String) {
        if (this.searchBy.value == value) return
        this.searchBy.value = value
    }

    fun checkFavorite(id:Int, btn: ToggleButton) {
        viewModelScope.launch {
            btn.isChecked = favoriteBeerUseCase.isFavorite(id)
        }
    }

    fun addDeleteFavorite(beerPresentationModelItem: BeerPresentationModelItem,isFavorite:Boolean) =
        viewModelScope.launch {
            favoriteBeerUseCase.addDeleteFavorite(beerPresentationModelItem,isFavorite)
        }
}
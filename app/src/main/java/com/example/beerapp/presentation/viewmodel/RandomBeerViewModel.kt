package com.example.beerapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerapp.domain.GetRandomBeerUseCase
import com.example.beerapp.presentation.model.BeerPresentationModelItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomBeerViewModel(private val getRandomBeerUseCase: GetRandomBeerUseCase) : ViewModel() {
    private val randomBeer = MutableLiveData<List<BeerPresentationModelItem>>()
    val getRandomBeer: LiveData<List<BeerPresentationModelItem>>
        get() = randomBeer

    init {
        getRandomBeer()
    }

    private fun getRandomBeer() {
        viewModelScope.launch(Dispatchers.IO) {
            randomBeer.postValue(getRandomBeerUseCase.getRandomBeer())
        }
    }
}
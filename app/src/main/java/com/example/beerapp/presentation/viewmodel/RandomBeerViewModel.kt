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
    private val randomBeer = MutableLiveData<BeerPresentationModelItem>()
    val getRandomBeer: LiveData<BeerPresentationModelItem>
        get() = randomBeer
    private val visibilityProgress = MutableLiveData<Boolean>()
    val getVisibilityProgress: LiveData<Boolean>
        get() = visibilityProgress


    init {
        getRandomBeer()
    }

    private fun getRandomBeer() {
        visibilityProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            randomBeer.postValue(getRandomBeerUseCase.getRandomBeer())
            visibilityProgress.postValue(false)
        }
    }
}
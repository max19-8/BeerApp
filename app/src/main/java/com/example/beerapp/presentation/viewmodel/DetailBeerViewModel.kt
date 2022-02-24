package com.example.beerapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.beerapp.domain.FavoriteBeerUseCase

class DetailBeerViewModel(
    private val favoriteBeerUseCase: FavoriteBeerUseCase
) : ViewModel() {
    suspend fun checkFavorite(id: Int) = favoriteBeerUseCase.isFavorite(id)
}
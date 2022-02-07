package com.example.beerapp.di

import com.example.beerapp.presentation.viewmodel.ListBeerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel{
        ListBeerViewModel(getListBeerUseCase = get())
    }
}
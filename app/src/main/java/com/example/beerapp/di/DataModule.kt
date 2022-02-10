package com.example.beerapp.di

import com.example.beerapp.data.repository.ListBeerRepositoryImpl
import com.example.beerapp.data.repository.RandomBeerRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single {
        ListBeerRepositoryImpl(apiService = get())
    }
    single {
        RandomBeerRepositoryImpl(apiService = get())
    }

}
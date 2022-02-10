package com.example.beerapp.di

import com.example.beerapp.domain.GetListBeerUseCase
import com.example.beerapp.domain.GetRandomBeerUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetListBeerUseCase(listBeerRepositoryImpl = get())
    }

    factory {
        GetRandomBeerUseCase(randomBeerRepositoryImpl = get())
    }
}
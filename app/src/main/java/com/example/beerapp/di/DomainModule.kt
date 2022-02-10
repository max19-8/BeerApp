package com.example.beerapp.di

import com.example.beerapp.domain.GetListBeerUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetListBeerUseCase(listBeerRepositoryImpl = get())
    }
}
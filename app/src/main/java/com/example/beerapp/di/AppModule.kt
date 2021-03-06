package com.example.beerapp.di


import android.app.Application
import androidx.room.Room
import com.example.beerapp.data.api.ApiService
import com.example.beerapp.database.CacheDataSource
import com.example.beerapp.database.FavoriteBeerDao
import com.example.beerapp.database.FavoriteBeerDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(okHttpClient = get()) }
    single { provideApiService(retrofit = get()) }
    single { provideDataBase(get()) }
    single { provideDao(dataBase = get()) }
}

private fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.punkapi.com/")
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

    fun provideDataBase(application: Application): CacheDataSource {
        return CacheDataSource.Base(application)
    }

    fun provideDao(dataBase: CacheDataSource): FavoriteBeerDao {
        return dataBase.getBeerDao()
    }
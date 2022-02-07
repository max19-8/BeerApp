//package com.example.beerapp.data.api
//
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.moshi.MoshiConverterFactory
//
//
//private const val BASE_URL = "https://api.punkapi.com"
//object RetrofitInstance {
//    private val retrofit by lazy {
//
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//
//         val moshi  = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()!!
//
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//    }
//    val apiService: ApiService by lazy {
//        retrofit.create(ApiService::class.java)
//    }
//}




//class RetrofitInstance {
//
//    private val retrofit by lazy {
//        val httpLoginInterceptor = HttpLoggingInterceptor()
//        httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(httpLoginInterceptor)
//            .build()
//
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//    }
//
//    companion object{
//       private const val BASE_URL = "https://api.punkapi.com"
//    }
//}
package com.example.beerapp.presentation.adapter

interface OnBeerClickListener {
   fun onBeerClick(beerId:Int,beerName:String,description:String,strengthDrinks:Float,imageUrl:String,hydrogenIndex:Float)
}
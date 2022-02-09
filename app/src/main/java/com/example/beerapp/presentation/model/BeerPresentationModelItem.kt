package com.example.beerapp.presentation.model

data class BeerPresentationModelItem(
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String,
    val strengthDrinks: Double,
    val hydrogenIndex: Double,
) {
}
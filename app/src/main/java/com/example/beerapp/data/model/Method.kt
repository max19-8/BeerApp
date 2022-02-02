package com.example.beerapp.data.model

data class Method(
    val fermentation: Fermentation,
    val mash_temp: List<MashTemp>,
    val twist: Any
)
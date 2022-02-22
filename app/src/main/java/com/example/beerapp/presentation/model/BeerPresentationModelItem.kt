package com.example.beerapp.presentation.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_beer")
data class BeerPresentationModelItem(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val tagline: String?,
    val firstBrewed: String?,
    val description: String?,
    val imageUrl: String?,
    val strengthDrinks: Double?,
    val hydrogenIndex: Double?,
):Parcelable
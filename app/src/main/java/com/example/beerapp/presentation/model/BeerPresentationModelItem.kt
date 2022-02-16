package com.example.beerapp.presentation.model

import android.os.Parcelable
import androidx.paging.PagingData
import androidx.paging.map
import com.example.beerapp.data.model.BeerRemoteModelItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerPresentationModelItem(
    val id: Int?,
    val name: String?,
    val tagline: String?,
    val firstBrewed: String?,
    val description: String?,
    val imageUrl: String?,
    val strengthDrinks: Double?,
    val hydrogenIndex: Double?,
):Parcelable
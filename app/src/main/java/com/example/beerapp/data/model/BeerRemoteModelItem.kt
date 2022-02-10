package com.example.beerapp.data.model

import android.os.Parcelable
import androidx.paging.PagingData
import androidx.paging.map
import com.example.beerapp.presentation.model.BeerPresentationModelItem
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerRemoteModelItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("first_brewed")
    val firstBrewed: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("abv")
    val strengthDrinks: Double?,
    @SerializedName("ph")
    val hydrogenIndex: Double?,
) :Parcelable

fun PagingData<BeerRemoteModelItem>.toPresentationBeer() = map {
    BeerPresentationModelItem(
        id = it.id,
        name = it.name,
        tagline = it.tagline,
        firstBrewed = it.firstBrewed,
        description = it.description,
        imageUrl = it.imageUrl,
        strengthDrinks = it.strengthDrinks, hydrogenIndex = it.hydrogenIndex
    )
}
fun BeerRemoteModelItem.toPresentationBeer()=
    BeerPresentationModelItem(
        id = this.id,
        name = this.name,
        tagline = this.tagline,
        firstBrewed = this.firstBrewed,
        description = this.description,
        imageUrl = this.imageUrl,
        strengthDrinks = this.strengthDrinks,
        hydrogenIndex = this.hydrogenIndex)
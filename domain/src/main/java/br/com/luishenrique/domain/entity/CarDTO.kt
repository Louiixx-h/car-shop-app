package br.com.luishenrique.domain.entity

import com.google.gson.annotations.SerializedName

data class CarDTO (
    @SerializedName("ID") val id: Int,
    @SerializedName("Make") val make: String,
    @SerializedName("Model") val model: String,
    @SerializedName("Version") val version: String,
    @SerializedName("Image") val image: String,
    @SerializedName("KM") val kM: Int,
    @SerializedName("Price") val price: String,
    @SerializedName("YearModel") val yearModel: Int,
    @SerializedName("YearFab") val yearFab: Int,
    @SerializedName("Color") val color: String,
    val isFavorite: Boolean = false
)
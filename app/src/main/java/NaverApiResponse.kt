package com.capstone.orakgarak

import com.google.gson.annotations.SerializedName

data class NaverApiResponse(
    @SerializedName("items")
    val addresses: List<Address>
)

data class Address(
    @SerializedName("title")
    val title: String,
    @SerializedName("mapx")
    val x: Double,
    @SerializedName("mapy")
    val y: Double
)

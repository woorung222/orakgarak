package com.capstone.orakgarak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Store(
    val name: String,
    val address: String,
    val phone: String,
    val description: String,
    val hours: String,
    val amenities: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable

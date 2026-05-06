package com.example.wishyouwerehere.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val name: String,
    val country: String,
    val date: String,
    var rating: Float,
    val imageResId: Int
) : Parcelable
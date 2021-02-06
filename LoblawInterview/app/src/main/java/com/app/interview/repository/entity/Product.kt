package com.app.interview.repository.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val code: String,
    val image: String,
    val name: String,
    val price: String,
    val type: String
) : Parcelable

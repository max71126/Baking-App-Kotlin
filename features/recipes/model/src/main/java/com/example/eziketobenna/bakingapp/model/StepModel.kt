package com.example.eziketobenna.bakingapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StepModel(
    val id: Int,
    val videoURL: String,
    val description: String,
    val shortDescription: String,
    val thumbnailURL: String
) : Parcelable

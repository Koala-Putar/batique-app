package com.example.batiqueapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Batik (
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val category: String,
    val isFavorite: Boolean,
    val latestAccess: Date?
): Parcelable
package com.example.batiqueapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "batik")
data class BatikEntity (
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: String,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "image")
        var image: String,

        @ColumnInfo(name = "description")
        var description: String,

        @ColumnInfo(name = "category")
        var category: String,

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
)
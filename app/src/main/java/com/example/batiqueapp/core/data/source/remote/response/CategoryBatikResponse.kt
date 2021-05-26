package com.example.batiqueapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CategoryBatikResponse(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

)

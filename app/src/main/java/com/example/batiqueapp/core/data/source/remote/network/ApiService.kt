package com.example.batiqueapp.core.data.source.remote.network

import com.example.batiqueapp.core.data.source.remote.response.BatikResponse
import com.example.batiqueapp.core.data.source.remote.response.CategoryBatikResponse
import retrofit2.http.GET

interface ApiService {

    @GET("batiks")
    suspend fun getBatik(): List<BatikResponse>

    @GET("category")
    suspend fun getCategory(): List<CategoryBatikResponse>

}
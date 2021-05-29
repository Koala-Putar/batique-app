package com.example.batiqueapp.core.data.source.remote

import android.util.Log
import com.example.batiqueapp.core.data.source.remote.network.ApiResponse
import com.example.batiqueapp.core.data.source.remote.network.ApiService
import com.example.batiqueapp.core.data.source.remote.response.BatikResponse
import com.example.batiqueapp.core.data.source.remote.response.CategoryBatikResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }

    }

    suspend fun getAllBatik(): Flow<ApiResponse<List<BatikResponse>>> {
        return flow {
            try {
                val response = apiService.getBatik()
                val dataArray = response
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllCategory(): Flow<ApiResponse<List<CategoryBatikResponse>>> {
        return flow {
            try {
                val response = apiService.getCategory()
                val dataArray = response
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}
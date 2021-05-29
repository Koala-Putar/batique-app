package com.example.batiqueapp.core.di

import android.content.Context
import com.example.batiqueapp.core.data.BatikRepository
import com.example.batiqueapp.core.data.source.local.LocalDataSource
import com.example.batiqueapp.core.data.source.local.room.BatikDatabase
import com.example.batiqueapp.core.data.source.remote.RemoteDataSource
import com.example.batiqueapp.core.data.source.remote.network.ApiConfig
import com.example.batiqueapp.core.domain.repository.IBatikRepository
import com.example.batiqueapp.core.domain.usecase.BatikInteractor
import com.example.batiqueapp.core.domain.usecase.BatikUseCase
import com.example.batiqueapp.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IBatikRepository {
        val database = BatikDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.batikDao())
        val appExecutors = AppExecutors()

        return BatikRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideBatikUseCase(context: Context): BatikUseCase {
        val repository = provideRepository(context)
        return BatikInteractor(repository)
    }
}
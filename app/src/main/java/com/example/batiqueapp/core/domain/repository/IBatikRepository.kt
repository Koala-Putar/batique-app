package com.example.batiqueapp.core.domain.repository

import com.example.batiqueapp.core.data.Resource
import com.example.batiqueapp.core.domain.model.Batik
import kotlinx.coroutines.flow.Flow

interface IBatikRepository {

    fun getAllBatik(): Flow<Resource<List<Batik>>>

    fun getAllFavoriteBatik(): Flow<List<Batik>>

    fun getCategoryBy(category: String): Flow<List<Batik>>

    fun setFavoriteBatik(batik: Batik, state: Boolean)

}
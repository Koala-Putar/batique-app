package com.example.batiqueapp.core.domain.usecase

import com.example.batiqueapp.core.data.Resource
import com.example.batiqueapp.core.domain.model.Batik
import com.example.batiqueapp.core.domain.model.Category
import kotlinx.coroutines.flow.Flow
import java.util.*

interface BatikUseCase {

    fun getAllBatik(): Flow<Resource<List<Batik>>>

    fun getBatikBySearch(search: String): Flow<List<Batik>>

    fun getAllCategory(): Flow<Resource<List<Category>>>

    fun getAllFavoriteBatik(): Flow<List<Batik>>

    fun getAllLatestAccess(): Flow<List<Batik>>

    fun getCategoryBy(category: String): Flow<List<Batik>>

    fun setFavoriteBatik(batik: Batik, state: Boolean)

    fun setLatestAccessDate(batik: Batik, newDate: Date)

}
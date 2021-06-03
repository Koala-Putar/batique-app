package com.example.batiqueapp.core.data.source.local

import com.example.batiqueapp.core.data.source.local.entity.BatikEntity
import com.example.batiqueapp.core.data.source.local.entity.CategoryEntity
import com.example.batiqueapp.core.data.source.local.room.BatikDao
import kotlinx.coroutines.flow.Flow
import java.util.*

class LocalDataSource private constructor(private val batikDao: BatikDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(batikDao: BatikDao) : LocalDataSource =
                instance ?: synchronized(this) {
                    instance ?: LocalDataSource(batikDao)
                }
    }

    fun getAllBatik(): Flow<List<BatikEntity>> = batikDao.getAllBatik()

    fun getAllCategory(): Flow<List<CategoryEntity>> = batikDao.getAllCategory()

    fun getAllFavoriteBatik(): Flow<List<BatikEntity>> = batikDao.getAllFavoriteBatik()

    fun getAllLatestAccess(): Flow<List<BatikEntity>> = batikDao.getAllLatestAccess()

    fun getCategoryBy(category: String): Flow<List<BatikEntity>> = batikDao.getCategoryBy(category)

    suspend fun insertBatik(batikList: List<BatikEntity>) = batikDao.insertBatik(batikList)

    suspend fun insertCategory(categoryList: List<CategoryEntity>) = batikDao.insertCategory(categoryList)

    fun setFavoriteBatik(batik: BatikEntity, newState: Boolean) {
        batik.isFavorite = newState
        batikDao.updateFavoriteBatik(batik)
    }

    fun setLatestAccessDate(batik: BatikEntity, newDate: Date) {
        batik.latestAccess = newDate
        batikDao.updateLatestAccessBatik(batik)
    }

}
package com.example.batiqueapp.core.data.source.local.room

import androidx.room.*
import com.example.batiqueapp.core.data.source.local.entity.BatikEntity
import com.example.batiqueapp.core.data.source.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BatikDao {

    @Query("SELECT * FROM batik")
    fun getAllBatik(): Flow<List<BatikEntity>>

    @Query("SELECT * FROM category")
    fun getAllCategory(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM batik WHERE category = :category")
    fun getCategoryBy(category: String): Flow<List<BatikEntity>>

    @Query("SELECT * FROM batik WHERE isFavorite = 1")
    fun getAllFavoriteBatik(): Flow<List<BatikEntity>>

    @Query("SELECT * FROM batik WHERE latestAccess IS NOT NULL ORDER BY latestAccess DESC")
    fun getAllLatestAccess(): Flow<List<BatikEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBatik(batik: List<BatikEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(batik: List<CategoryEntity>)

    @Update
    fun updateFavoriteBatik(batik: BatikEntity)

    @Update
    fun updateLatestAccessBatik(batik: BatikEntity)

}
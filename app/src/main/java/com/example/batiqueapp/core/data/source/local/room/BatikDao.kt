package com.example.batiqueapp.core.data.source.local.room

import androidx.room.*
import com.example.batiqueapp.core.data.source.local.entity.BatikEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BatikDao {

    @Query("SELECT * FROM batik")
    fun getAllBatik(): Flow<List<BatikEntity>>

    @Query("SELECT * FROM batik where category = :category")
    fun getCategoryBy(category: String): Flow<List<BatikEntity>>

    @Query("SELECT * FROM batik where isFavorite = 1")
    fun getAllFavoriteBatik(): Flow<List<BatikEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBatik(batik: List<BatikEntity>)

    @Update
    fun updateFavoriteBatik(batik: BatikEntity)

}
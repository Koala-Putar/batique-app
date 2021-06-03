package com.example.batiqueapp.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.batiqueapp.core.data.source.local.entity.BatikEntity
import com.example.batiqueapp.core.data.source.local.entity.CategoryEntity
import com.example.batiqueapp.core.utils.DateTypeConverter

@Database(entities = [BatikEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class BatikDatabase : RoomDatabase() {

    abstract fun batikDao(): BatikDao

    companion object {

        @Volatile
        private var INSTANCE: BatikDatabase? = null

        fun getInstance(context: Context): BatikDatabase =
                INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                            context.applicationContext,
                            BatikDatabase::class.java,
                            "Batik.db"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                    instance
                }
    }
}
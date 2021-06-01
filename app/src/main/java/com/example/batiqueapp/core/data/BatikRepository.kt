package com.example.batiqueapp.core.data

import com.example.batiqueapp.core.data.source.local.LocalDataSource
import com.example.batiqueapp.core.data.source.remote.RemoteDataSource
import com.example.batiqueapp.core.data.source.remote.network.ApiResponse
import com.example.batiqueapp.core.data.source.remote.response.BatikResponse
import com.example.batiqueapp.core.data.source.remote.response.CategoryBatikResponse
import com.example.batiqueapp.core.domain.model.Batik
import com.example.batiqueapp.core.domain.model.Category
import com.example.batiqueapp.core.domain.repository.IBatikRepository
import com.example.batiqueapp.core.utils.AppExecutors
import com.example.batiqueapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BatikRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IBatikRepository{

    companion object {
        @Volatile
        private var instance: BatikRepository? = null

        fun getInstance(
                remoteData: RemoteDataSource,
                localData: LocalDataSource,
                appExecutors: AppExecutors
        ): BatikRepository =
                instance ?: synchronized(this) {
                    instance ?: BatikRepository(remoteData, localData, appExecutors)
                }
    }

    override fun getAllBatik(): Flow<Resource<List<Batik>>> =
        object : NetworkBoundResource<List<Batik>, List<BatikResponse>>() {
            override fun loadFromDB(): Flow<List<Batik>> {
                return localDataSource.getAllBatik().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Batik>?): Boolean =
                data == null || data.isEmpty()
                    // true ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<BatikResponse>>> =
                    remoteDataSource.getAllBatik()

            override suspend fun saveCallResult(data: List<BatikResponse>) {
                val batikList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertBatik(batikList)
            }
        }.asFlow()

    override fun getAllCategory(): Flow<Resource<List<Category>>> =
        object : NetworkBoundResource<List<Category>, List<CategoryBatikResponse>>() {
            override fun loadFromDB(): Flow<List<Category>> {
                return localDataSource.getAllCategory().map {
                    DataMapper.mapCategoryEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Category>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<CategoryBatikResponse>>> =
                remoteDataSource.getAllCategory()

            override suspend fun saveCallResult(data: List<CategoryBatikResponse>) {
                val categoryList = DataMapper.mapCategoryResponseToEntities(data)
                localDataSource.insertCategory(categoryList)
            }
        }.asFlow()


    override fun getAllFavoriteBatik(): Flow<List<Batik>> {
        return localDataSource.getAllFavoriteBatik().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getCategoryBy(category: String): Flow<List<Batik>> {
        return localDataSource.getCategoryBy(category).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteBatik(batik: Batik, state: Boolean) {
        val batikEntity = DataMapper.mapDomainToEntity(batik)
        appExecutors.diskIO().execute { localDataSource.setFavoriteBatik(batikEntity, state) }
    }

}
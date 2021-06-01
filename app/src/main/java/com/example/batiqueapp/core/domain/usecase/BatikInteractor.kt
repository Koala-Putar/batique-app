package com.example.batiqueapp.core.domain.usecase

import com.example.batiqueapp.core.data.Resource
import com.example.batiqueapp.core.domain.model.Batik
import com.example.batiqueapp.core.domain.model.Category
import com.example.batiqueapp.core.domain.repository.IBatikRepository
import kotlinx.coroutines.flow.Flow

class BatikInteractor(private val batikRepository: IBatikRepository): BatikUseCase {

    override fun getAllBatik(): Flow<Resource<List<Batik>>> = batikRepository.getAllBatik()

    override fun getAllCategory(): Flow<Resource<List<Category>>> = batikRepository.getAllCategory()

    override fun getAllFavoriteBatik(): Flow<List<Batik>> = batikRepository.getAllFavoriteBatik()

    override fun getCategoryBy(category: String): Flow<List<Batik>> = batikRepository.getCategoryBy(category)

    override fun setFavoriteBatik(batik: Batik, state: Boolean) = batikRepository.setFavoriteBatik(batik, state)

}
package com.example.batiqueapp.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.batiqueapp.core.domain.usecase.BatikUseCase

class ArchiveViewModel (private val batikUseCase: BatikUseCase) : ViewModel() {

    private lateinit var categoryName: String

    fun setSelectedCategory(categoryName: String) {
        this.categoryName = categoryName
    }

    val categoryBy = batikUseCase.getCategoryBy(categoryName).asLiveData()

}
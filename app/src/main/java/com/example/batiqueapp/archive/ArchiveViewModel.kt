package com.example.batiqueapp.archive

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.batiqueapp.core.domain.model.Batik
import com.example.batiqueapp.core.domain.usecase.BatikUseCase

class ArchiveViewModel (private val batikUseCase: BatikUseCase) : ViewModel() {

    fun showCategoryBy(categoryName: String): LiveData<List<Batik>> {
        return batikUseCase.getCategoryBy(categoryName).asLiveData()
    }

}
package com.example.batiqueapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.batiqueapp.core.domain.model.Batik
import com.example.batiqueapp.core.domain.usecase.BatikUseCase

class SearchViewModel (private val batikUseCase: BatikUseCase) : ViewModel() {

    fun showBySearch(searchQuery: String): LiveData<List<Batik>> {
        return batikUseCase.getBatikBySearch(searchQuery).asLiveData()
    }

}
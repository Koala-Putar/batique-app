package com.example.batiqueapp.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.batiqueapp.core.domain.usecase.BatikUseCase

class ExploreViewModel(private val batikUseCase: BatikUseCase) : ViewModel() {

    val category = batikUseCase.getAllCategory().asLiveData()

}
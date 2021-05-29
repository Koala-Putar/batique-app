package com.example.batiqueapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.batiqueapp.core.domain.usecase.BatikUseCase

class HomeViewModel(private val batikUseCase: BatikUseCase) : ViewModel() {

    val batik = batikUseCase.getAllBatik().asLiveData()

}
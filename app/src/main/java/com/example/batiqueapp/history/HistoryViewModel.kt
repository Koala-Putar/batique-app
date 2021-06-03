package com.example.batiqueapp.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.batiqueapp.core.domain.usecase.BatikUseCase

class HistoryViewModel(private val batikUseCase: BatikUseCase) : ViewModel() {

    val latestAccess = batikUseCase.getAllLatestAccess().asLiveData()

}
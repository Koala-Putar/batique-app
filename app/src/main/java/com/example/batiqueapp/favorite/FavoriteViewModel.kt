package com.example.batiqueapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.batiqueapp.core.domain.usecase.BatikUseCase

class FavoriteViewModel(private val batikUseCase: BatikUseCase) : ViewModel() {

    val favoriteBatik = batikUseCase.getAllFavoriteBatik().asLiveData()

}
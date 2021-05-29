package com.example.batiqueapp.detail

import androidx.lifecycle.ViewModel
import com.example.batiqueapp.core.domain.model.Batik
import com.example.batiqueapp.core.domain.usecase.BatikUseCase

class DetailViewModel (private val batikUseCase: BatikUseCase) : ViewModel() {

    fun setFavoriteBatik(batik: Batik, newStatus:Boolean) =
        batikUseCase.setFavoriteBatik(batik, newStatus)

}
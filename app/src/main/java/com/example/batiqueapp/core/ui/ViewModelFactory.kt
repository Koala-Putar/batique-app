package com.example.batiqueapp.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.batiqueapp.archive.ArchiveViewModel
import com.example.batiqueapp.core.di.Injection
import com.example.batiqueapp.core.domain.usecase.BatikUseCase
import com.example.batiqueapp.detail.DetailViewModel
import com.example.batiqueapp.explore.ExploreViewModel
import com.example.batiqueapp.favorite.FavoriteViewModel
import com.example.batiqueapp.home.HomeViewModel

class ViewModelFactory private constructor(private val batikUseCase: BatikUseCase) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(
                            Injection.provideBatikUseCase(context)
                    )
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
            when {
                modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                    HomeViewModel(batikUseCase) as T
                }
                modelClass.isAssignableFrom(ExploreViewModel::class.java) -> {
                    ExploreViewModel(batikUseCase) as T
                }
                modelClass.isAssignableFrom(ArchiveViewModel::class.java) -> {
                    ArchiveViewModel(batikUseCase) as T
                }
                modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                    FavoriteViewModel(batikUseCase) as T
                }
                modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                    DetailViewModel(batikUseCase) as T
                }
                else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
            }

}
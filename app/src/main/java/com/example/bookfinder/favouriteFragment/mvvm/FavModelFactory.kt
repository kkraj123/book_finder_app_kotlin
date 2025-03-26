package com.example.bookfinder.favouriteFragment.mvvm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookfinder.homeFragment.viewModel.HomeModel
import com.example.bookfinder.homeFragment.viewModel.HomeRepository
import javax.inject.Inject

class FavModelFactory @Inject constructor(private val favRepository: FavRepository,val context: Context): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavModel(favRepository, context) as T
    }
}

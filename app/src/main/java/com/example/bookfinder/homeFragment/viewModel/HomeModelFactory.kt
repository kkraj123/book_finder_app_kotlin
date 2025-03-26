package com.example.bookfinder.homeFragment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class HomeModelFactory @Inject constructor(private val homeRepository: HomeRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeModel(homeRepository) as T
    }
}
        
package com.example.bookfinder.homeFragment.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.PopularTechBookModel
import com.example.bookfinder.network.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    var technicalBooksResponse = MutableLiveData<NetworkResult<PopularTechBookModel>>()
    var bookCategoryResponse = MutableLiveData<NetworkResult<PopularTechBookModel>>()

    fun getPopularTechBooks() {
        viewModelScope.launch {
            try {
                technicalBooksResponse.value = NetworkResult.Loading()
                technicalBooksResponse.value = homeRepository.getPopularTechBooks()
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    fun getBookCategoryItems(categoryBook: String) {
        viewModelScope.launch {
            try {
                bookCategoryResponse.value = NetworkResult.Loading()
                bookCategoryResponse.value = homeRepository.getBookCategoryItems(categoryBook)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
package com.example.bookfinder.homeFragment.viewModel

import android.content.Context
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.PopularTechBookModel
import com.example.bookfinder.network.NetworkResult
import com.example.bookfinder.network.NetworkServices
import com.example.bookfinder.network.handleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(var context: Context){
    private val networkServices: NetworkServices = NetworkServices(context)

    suspend fun getPopularTechBooks(): NetworkResult<PopularTechBookModel> {
       return withContext(Dispatchers.IO){
            val response = networkServices.getPopularBooks("subject:Technology", "AIzaSyBfIE5m5PlI1hdJGNNz9CrhOW9mWSWBCGw")
            response.handleResponse()
       }

    }
    suspend fun getBookCategoryItems(categoryBook: String) : NetworkResult<PopularTechBookModel>{
        return withContext(Dispatchers.IO){
            val bookCategoryItemsResponse = networkServices.getBookCategory(categoryBook,"AIzaSyBfIE5m5PlI1hdJGNNz9CrhOW9mWSWBCGw")
            bookCategoryItemsResponse.handleResponse()
        }
    }
}
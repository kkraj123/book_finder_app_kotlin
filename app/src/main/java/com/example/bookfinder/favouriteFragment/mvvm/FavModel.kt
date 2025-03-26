package com.example.bookfinder.favouriteFragment.mvvm

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookfinder.Utility.LoadingState
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.VolumeInfo
import com.example.bookfinder.network.helper.TmkSharePreference
import com.example.bookfinder.roomDatabase.BookDatabase
import com.example.bookfinder.roomDatabase.BooksDao
import kotlinx.coroutines.launch

class FavModel(private val repository: FavRepository, val context: Context) : ViewModel() {
    private val booksDao: BooksDao = BookDatabase.getDatabase(context).bookDao()
    private val _allBooks = MutableLiveData<MutableList<VolumeInfo>>()
    val allBooks: LiveData<MutableList<VolumeInfo>> get() = _allBooks

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> get() = _loadingState


    fun getTitleExit(volumeInfo: VolumeInfo, isTag: String) {
        viewModelScope.launch {
            val count = repository.getTitleExit(volumeInfo.title)
            if (count == 0) {
                booksDao.insert(volumeInfo)
                Toast.makeText(context, "Book added", Toast.LENGTH_SHORT).show()

            }else {
                if (isTag == "isHome"){
                    Toast.makeText(context, "Book already added", Toast.LENGTH_SHORT).show()
                    volumeInfo.isFavorite = true
                }else {
                    repository.deleteItem(volumeInfo)
                }
            }
        }
    }

    fun fetchAllBooks() {
        _loadingState.value = LoadingState.LOADING  // Set loading state before starting the fetch
        viewModelScope.launch {
            try {
                val books = booksDao.getAllBooks()
                _allBooks.postValue(books)
                _loadingState.postValue(LoadingState.SUCCESS)  // Set success after data is fetched
            } catch (e: Exception) {
                _loadingState.postValue(LoadingState.ERROR)  // Set error if something goes wrong
            }
        }
    }

    fun deleteItem(volumeInfo: VolumeInfo) {
        viewModelScope.launch {
            repository.deleteItem(volumeInfo)
        }
    }
}
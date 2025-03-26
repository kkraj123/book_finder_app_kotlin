package com.example.bookfinder.favouriteFragment.mvvm

import android.widget.Toast
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.VolumeInfo
import com.example.bookfinder.network.helper.TmkSharePreference
import com.example.bookfinder.roomDatabase.BooksDao

class FavRepository (private val dao: BooksDao,val tmkSharePreference: TmkSharePreference) {

    suspend fun insertBook(volumeInfo: VolumeInfo) { dao.insert(volumeInfo) }

    suspend fun updateFavoriteStatus(bookDao: BooksDao, bookId: Int, status: Boolean) {
        bookDao.updateFavoriteStatus(bookId, status)
    }

    suspend fun getTitleExit(title: String): Int = dao.isTitleExists(title)
    suspend fun deleteItem(volumeInfo: VolumeInfo) = dao.delete(volumeInfo)

}
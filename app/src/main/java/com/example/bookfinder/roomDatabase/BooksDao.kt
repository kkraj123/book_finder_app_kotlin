package com.example.bookfinder.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.VolumeInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Query("SELECT COUNT(*) FROM book_item WHERE title = :title")
    suspend fun isTitleExists(title: String): Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(volumeInfo: VolumeInfo)

    @Query("SELECT * FROM book_item WHERE title LIKE :query")
    suspend fun searchBooks(query: String): List<VolumeInfo>

    @Query("SELECT * FROM book_item")
    suspend fun getAllBooks(): MutableList<VolumeInfo>

    @Delete
    suspend fun delete(volumeInfo: VolumeInfo)

    @Query("UPDATE book_item SET isFavorite = :isFavorite WHERE id = :bookId")
    suspend fun updateFavoriteStatus(bookId: Int, isFavorite: Boolean)

    @Query("SELECT * FROM book_item WHERE isFavorite = 1")
    fun getFavoriteBooks(): Flow<List<VolumeInfo>>
}
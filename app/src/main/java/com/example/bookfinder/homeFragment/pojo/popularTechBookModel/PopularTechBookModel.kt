package com.example.bookfinder.homeFragment.pojo.popularTechBookModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bookfinder.homeFragment.jsonConveter.Converters
import java.io.Serializable

data class PopularTechBookModel(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
) : Serializable
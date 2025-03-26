package com.example.bookfinder.homeFragment.pojo.popularTechBookModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bookfinder.homeFragment.jsonConveter.Converters
import java.io.Serializable

data class Item(
    val accessInfo: AccessInfo,
    val etag: String,
    val id: String,
    val kind: String,
    val saleInfo: SaleInfo,
    val selfLink: String,
    val volumeInfo: VolumeInfo
): Serializable
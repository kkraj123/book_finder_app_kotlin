package com.example.bookfinder.homeFragment.pojo.popularTechBookModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bookfinder.homeFragment.jsonConveter.Converters
import java.io.Serializable

@Entity(tableName = "book_item")
@TypeConverters(Converters::class)
data class VolumeInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val allowAnonLogging: Boolean = false,
    val authors: List<String> = emptyList(),
    val averageRating: Double = 0.0,
    val canonicalVolumeLink: String = "",
    val categories: List<String> = emptyList(),
    val contentVersion: String = "",
    val description: String = "",
    val imageLinks: ImageLinks = ImageLinks("",""),
    val industryIdentifiers: List<IndustryIdentifier> = emptyList(),
    val infoLink: String = "",
    val language: String = "",
    val maturityRating: String = "",
    val pageCount: Int = 0,
    val panelizationSummary: PanelizationSummary = PanelizationSummary(false, false),
    val previewLink: String = "",
    val printType: String = "",
    val publishedDate: String = "",
    val publisher: String = "",
    val ratingsCount: Int = 0,
    val readingModes: ReadingModes = ReadingModes(false, false),
    val subtitle: String = "",
    val title: String = "",
    var isFavorite: Boolean = false
): Serializable
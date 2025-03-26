package com.example.bookfinder.homeFragment.jsonConveter

import androidx.room.TypeConverter
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.AccessInfo
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.ImageLinks
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.IndustryIdentifier
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.Item
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.PanelizationSummary
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.ReadingModes
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.SaleInfo
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.VolumeInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        return value?.let {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(value, type)
        }
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromImageLinks(value: String?): ImageLinks? {
        return value?.let {
            gson.fromJson(value, ImageLinks::class.java)
        }
    }

    @TypeConverter
    fun toImageLinks(imageLinks: ImageLinks?): String {
        return gson.toJson(imageLinks)
    }
    @TypeConverter
    fun fromIndustryIdentifierList(value: List<IndustryIdentifier>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toIndustryIdentifierList(value: String): List<IndustryIdentifier>? {
        val type = object : TypeToken<List<IndustryIdentifier>>() {}.type
        return gson.fromJson(value, type)
    }
    @TypeConverter
    fun fromPanelizationSummary(value: PanelizationSummary?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPanelizationSummary(value: String): PanelizationSummary? {
        return gson.fromJson(value, PanelizationSummary::class.java)
    }
    @TypeConverter
    fun fromReadingModes(value: ReadingModes?): String {
        return gson.toJson(value)
    }

    // Convert JSON String back to ReadingModes
    @TypeConverter
    fun toReadingModes(value: String): ReadingModes? {
        return gson.fromJson(value, ReadingModes::class.java)
    }
}

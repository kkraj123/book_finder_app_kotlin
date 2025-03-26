package com.example.bookfinder.network.helper

import android.content.Context
import android.content.SharedPreferences

class TmkSharePreference(val context: Context) {
    val MODE = "tmksharepreference"
    var sharePreference: SharedPreferences = context.getSharedPreferences(MODE, Context.MODE_PRIVATE)

    val EXISTING_ITEM = "EXISTING_ITEM"

    fun getExistingItem(): Boolean {
        return sharePreference.getBoolean(EXISTING_ITEM, true)
    }

    fun setExistingItem(existingApp: Boolean) {
        sharePreference.edit().putBoolean(EXISTING_ITEM, existingApp).apply()
    }

}
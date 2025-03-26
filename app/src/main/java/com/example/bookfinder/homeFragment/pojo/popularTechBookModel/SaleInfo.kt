package com.example.bookfinder.homeFragment.pojo.popularTechBookModel

import java.io.Serializable

data class SaleInfo(
    val country: String,
    val isEbook: Boolean,
    val saleability: String
): Serializable
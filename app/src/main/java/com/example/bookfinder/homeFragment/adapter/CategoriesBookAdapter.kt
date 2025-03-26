package com.example.bookfinder.homeFragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookfinder.R
import com.example.bookfinder.databinding.CategoryBookSampleBinding
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.Item
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.VolumeInfo
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class CategoriesBookAdapter () : RecyclerView.Adapter<CategoriesBookAdapter.PopularTechBookViewHolder>() {
    private var categoryBookList: List<Item> = ArrayList<Item>()
    private lateinit var categoriesBookCallback: CategoriesBookCallback

    fun setOnClickPopularTechBook(categoriesBookCallback: CategoriesBookCallback){
        this.categoriesBookCallback = categoriesBookCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTechBookViewHolder {
        val binding = CategoryBookSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularTechBookViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryBookList.size
    }
    fun updateList(newList: List<Item>) {
        categoryBookList = emptyList()
        categoryBookList = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PopularTechBookViewHolder, position: Int) {
        val bookItem = categoryBookList[position]
        holder.binding.bookTitleTV.text = bookItem.volumeInfo.title
        val encodedUrl = bookItem.volumeInfo.imageLinks?.thumbnail
        val decodeUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
        Log.d("img","decodeUrl :$decodeUrl")
        Glide.with(holder.binding.bookImg.context)
            .load(decodeUrl)
            .error(R.drawable.sample_book)
            .into(holder.binding.bookImg)

        holder.binding.bookItem.setOnClickListener {
            categoriesBookCallback.CategoryBook(bookItem.volumeInfo)
        }
    }
    inner class PopularTechBookViewHolder(var binding: CategoryBookSampleBinding): RecyclerView.ViewHolder(binding.root)

}
interface CategoriesBookCallback{
    fun CategoryBook(volumeInfo: VolumeInfo)
}
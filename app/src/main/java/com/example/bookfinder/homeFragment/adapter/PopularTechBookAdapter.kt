package com.example.bookfinder.homeFragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookfinder.R
import com.example.bookfinder.databinding.PupolurTechBookSamplleBinding
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.Item
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.VolumeInfo
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class PopularTechBookAdapter(var popularTechItemList: List<Item>) :RecyclerView.Adapter<PopularTechBookAdapter.PopularTechBookViewHolder>() {

   private lateinit var clickOnPopularTechBook: ClickOnPopularTechBook

   fun setOnClickPopularTechBook(clickOnPopularTechBook: ClickOnPopularTechBook){
       this.clickOnPopularTechBook = clickOnPopularTechBook
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTechBookViewHolder {
        val binding = PupolurTechBookSamplleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularTechBookViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return popularTechItemList.size
    }

    override fun onBindViewHolder(holder: PopularTechBookViewHolder, position: Int) {
        val bookItem = popularTechItemList[position]
        holder.binding.bookTitleTV.text = bookItem.volumeInfo.title
        val encodedUrl = bookItem.volumeInfo.imageLinks?.thumbnail
        val decodeUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
        Log.d("img","decodeUrl :$decodeUrl")
        Glide.with(holder.binding.bookImg.context)
            .load(decodeUrl)
            .error(R.drawable.sample_book)
            .into(holder.binding.bookImg)

        holder.binding.bookItem.setOnClickListener {
            clickOnPopularTechBook.PopularTechBookItem(bookItem.volumeInfo)
        }
    }
    inner class PopularTechBookViewHolder(var binding: PupolurTechBookSamplleBinding): RecyclerView.ViewHolder(binding.root)

}
interface ClickOnPopularTechBook{
    fun PopularTechBookItem(volumeInfo: VolumeInfo)
}
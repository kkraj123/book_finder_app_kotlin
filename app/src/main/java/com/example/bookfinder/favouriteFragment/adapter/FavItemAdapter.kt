package com.example.bookfinder.favouriteFragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookfinder.R
import com.example.bookfinder.databinding.PupolurTechBookSamplleBinding
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.VolumeInfo
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class FavItemAdapter(private var volumeInfo: List<VolumeInfo>) : RecyclerView.Adapter<FavItemAdapter.FavItemViewHolder>(){
    private lateinit var favItemCallback: FavItemCallback

    fun setOnClickPopularTechBook(favItemCallback: FavItemCallback){
        this.favItemCallback = favItemCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavItemViewHolder {
        val binding = PupolurTechBookSamplleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return volumeInfo.size
    }

    fun updateList(listItem : List<VolumeInfo>){
        volumeInfo = emptyList()
        volumeInfo = listItem
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FavItemViewHolder, position: Int) {
        val bookItem = volumeInfo[position]
        holder.binding.bookTitleTV.text = bookItem.title
        val encodedUrl = bookItem.imageLinks.thumbnail
        val decodeUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
        Log.d("img","decodeUrl :$decodeUrl")
        Glide.with(holder.binding.bookImg.context)
            .load(decodeUrl)
            .error(R.drawable.sample_book)
            .into(holder.binding.bookImg)

        holder.binding.bookItem.setOnClickListener {
            favItemCallback.FavItemListener(bookItem)
        }
    }

    inner class FavItemViewHolder(val binding: PupolurTechBookSamplleBinding) : RecyclerView.ViewHolder(binding.root)

}
interface FavItemCallback{
    fun FavItemListener(volumeInfo: VolumeInfo)
}
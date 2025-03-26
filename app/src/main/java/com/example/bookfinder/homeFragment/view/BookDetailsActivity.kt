package com.example.bookfinder.homeFragment.view

import AutherListAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bookfinder.R
import com.example.bookfinder.databinding.ActivityBookDetailsBinding
import com.example.bookfinder.favouriteFragment.mvvm.FavModel
import com.example.bookfinder.favouriteFragment.mvvm.FavModelFactory
import com.example.bookfinder.favouriteFragment.mvvm.FavRepository
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.VolumeInfo
import com.example.bookfinder.network.helper.TmkSharePreference
import com.example.bookfinder.roomDatabase.BookDatabase
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class BookDetailsActivity : AppCompatActivity() {
    val TAG = "BookDetailsActivity"
    private lateinit var binding: ActivityBookDetailsBinding
    private lateinit var volumeInfo: VolumeInfo
    private lateinit var favModelFactory: FavModelFactory
    private lateinit var favModel: FavModel
    private lateinit var tmkSharePreference: TmkSharePreference
    private var isChecked = false
    private var isTag =""
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        tmkSharePreference = TmkSharePreference(this@BookDetailsActivity)
        val db = BookDatabase.getDatabase(this@BookDetailsActivity)
        favModelFactory = FavModelFactory(
            FavRepository(db.bookDao(), tmkSharePreference),
            this@BookDetailsActivity
        )
        favModel = ViewModelProvider(this, favModelFactory)[FavModel::class.java]
        setContentView(binding.root)
        if (intent != null) {
            volumeInfo = (intent.getSerializableExtra("bookDetails") as? VolumeInfo)!!
            isChecked = intent.getBooleanExtra("isChecked",false)
            isTag = intent.getStringExtra("isTag")!!
            volumeInfo.apply {
                binding.subTitleTV.text = title
                binding.publisherNdDateTV.text = publisher + " " + "(" + publishedDate + ")"
                binding.contentVersionTV.text = contentVersion
                binding.pagesCounterNdLanguageTV.text =
                    pageCount.toString() + " Pages" + " |" + language
                binding.bookDescriptionTV.text = description


                val autherListAdapter = AutherListAdapter(autherList = authors!!)
                val linaerLayoutManager = LinearLayoutManager(this@BookDetailsActivity)
                binding.autherListItem.layoutManager = linaerLayoutManager
                binding.autherListItem.adapter = autherListAdapter


                val encodedUrl = volumeInfo.imageLinks?.thumbnail
                val decodeUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
                Glide.with(this@BookDetailsActivity)
                    .load(decodeUrl)
                    .error(R.drawable.sample_book)
                    .into(binding.bookImg)

            }
        }
        clickListener()

    }

    private fun clickListener() {
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.favBtn.setImageResource(if (isChecked) R.drawable.ic_drawable_fav_icon else R.drawable.add_fav_icon_tbn)

        binding.favBtn.setOnClickListener {
            val isCurrentlyFavorite = volumeInfo.isFavorite
            volumeInfo.isFavorite = !isCurrentlyFavorite

            binding.favBtn.setImageResource(
                if (volumeInfo.isFavorite) R.drawable.ic_drawable_fav_icon
                else R.drawable.add_fav_icon_tbn
            )

            favModel.getTitleExit(volumeInfo, isTag)

            if (isTag == "isHome" && volumeInfo.isFavorite) {
                binding.favBtn.isEnabled = false
            }
        }

    }


}


package com.example.bookfinder.network

import android.content.Context
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.PopularTechBookModel
import com.example.bookfinder.network.apiLoger.ApiLogger
import com.example.bookfinder.network.helper.categoryBookItems
import com.example.bookfinder.network.helper.popularBooks
import com.example.bookfinder.network.helper.server
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.File
import java.util.concurrent.TimeUnit

interface NetworkServices {
    companion object {
        private const val TAG: String = "NetworkClient"
        private const val cacheSize = (10 * 1024 * 1024).toLong()

        operator fun invoke(/*connectionInterceptor: ConnectionInterceptor,*/ context: Context): NetworkServices {
          val loginInterceptor = HttpLoggingInterceptor(ApiLogger())
              .setLevel(HttpLoggingInterceptor.Level.BODY)

             val cacheDir = File(context.cacheDir, "http_cache")

            val cache = Cache(cacheDir, cacheSize)

            val okHttpClient = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loginInterceptor)
                .connectTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(NetworkServices::class.java)
        }
    }
  @GET(popularBooks)
  suspend fun getPopularBooks(@Query("q")popularBook: String, @Query("key")myKey: String) : Response<PopularTechBookModel>

  @GET(categoryBookItems)
  suspend fun getBookCategory(@Query("q")categoryBooks: String, @Query("key")myKey: String) : Response<PopularTechBookModel>
}
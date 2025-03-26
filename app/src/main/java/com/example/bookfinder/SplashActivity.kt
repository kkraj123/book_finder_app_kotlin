package com.example.bookfinder

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bookfinder.Utility.Utility
import com.example.bookfinder.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Utility().isInternetAvailable(this@SplashActivity)) {
            Handler(mainLooper).postDelayed({
                startActivity(
                    Intent(
                        this@SplashActivity,
                        MainActivity::class.java
                    ).apply { Intent.FLAG_ACTIVITY_SINGLE_TOP })
                finish()
            }, 2000)
        }else{
            AlertDialog.Builder(this@SplashActivity)
                .setTitle("Internet")
                .setMessage("Internet is not available, Are you sure you want to continue offline?")
                .setPositiveButton("Yes") { dialog, _ ->
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("load_favorite", !Utility().isInternetAvailable(this))
                    startActivity(intent)
                    finish()
                    dialog.dismiss()
                }.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

    }
}
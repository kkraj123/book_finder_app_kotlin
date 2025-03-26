package com.example.bookfinder

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bookfinder.Utility.Utility
import com.example.bookfinder.databinding.ActivityMainBinding
import com.example.bookfinder.favouriteFragment.view.FavouriteFragment
import com.example.bookfinder.homeFragment.view.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loadFavorite = intent.getBooleanExtra("load_favorite", false)

        if (savedInstanceState == null) {
            if (loadFavorite){
                fragment = FavouriteFragment()
                binding.bottomNavigation.selectedItemId = R.id.favouriteBtn
            }else{
                fragment = HomeFragment()
                binding.bottomNavigation.selectedItemId = R.id.homeBtn
            }
            replaceFragment(fragment)
            /*val fragment = if (loadFavorite) FavouriteFragment()  else HomeFragment()
            replaceFragment(fragment)*/
        }
        binding.bottomNavigation.setOnNavigationItemSelectedListener(object: BottomNavigationView.OnNavigationItemSelectedListener{
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.homeBtn ->{
                        if (Utility().isInternetAvailable(this@MainActivity)) {
                            replaceFragment(HomeFragment())
                        } else {
                            showNoInternetAlert()
                        }
                        return true
                    }
                    R.id.favouriteBtn ->{
                        replaceFragment(FavouriteFragment())
                        return true
                    }
                }
                return false
            }

        })

    }
    private fun showNoInternetAlert() {
        AlertDialog.Builder(this)
            .setTitle("No Internet")
            .setMessage("You're offline. Please check your connection.")
            .setPositiveButton("OK", null)
            .show()
    }
    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayoutID, fragment)
        transaction.commit()
    }
}
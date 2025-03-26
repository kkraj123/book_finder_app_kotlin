package com.example.bookfinder.Utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.example.bookfinder.customClass.CustomProgressDialogFragment

class Utility {
    fun showCustomDialog(activity: AppCompatActivity): CustomProgressDialogFragment {
        val customProgressDialogFragment: CustomProgressDialogFragment = CustomProgressDialogFragment()
        customProgressDialogFragment.show(activity.supportFragmentManager, "dialog")
        return customProgressDialogFragment
    }

    fun showInfoDialog(context: Context, title: String?, msg: String?): MaterialDialog {
        return MaterialDialog(context).show {
            title(text = title)
            message(text = msg)
            positiveButton(text = "Ok") { dialog->
                dialog.dismiss()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}
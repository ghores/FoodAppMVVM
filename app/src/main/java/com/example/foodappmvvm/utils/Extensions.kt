package com.example.foodappmvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import com.google.android.material.snackbar.Snackbar

//Check network
fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = cm.activeNetworkInfo
    return info != null && info.isConnected
}

//SnackBar
fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}
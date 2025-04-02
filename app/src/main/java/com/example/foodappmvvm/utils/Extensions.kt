package com.example.foodappmvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.foodappmvvm.R
import com.google.android.material.snackbar.Snackbar

//Spinner
fun Spinner.setupListWithAdapter(list: MutableList<out Any>, callback: (String) -> Unit) {
    val adapter = ArrayAdapter(context, R.layout.item_spinner, list)
    adapter.setDropDownViewResource(R.layout.item_spinner_list)
    this.adapter = adapter
    this.onItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            callback(list[position].toString())
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

    }
}

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
package com.example.foodappmvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodappmvvm.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foods)

    }
}
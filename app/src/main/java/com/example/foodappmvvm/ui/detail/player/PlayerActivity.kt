package com.example.foodappmvvm.ui.detail.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodappmvvm.R
import com.example.foodappmvvm.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    //Binding
    private var _binding: ActivityPlayerBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        //InitViews
        binding?.apply {
        }
    }
}
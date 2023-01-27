package com.example.albertsonscodingchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.albertsonscodingchallenge.viewmodels.dictionary.MainViewModel
import com.example.albertsonscodingchallenge.R
import com.example.albertsonscodingchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        activityMainBinding.viewModel = mainViewModel
        activityMainBinding.lifecycleOwner = this
        val editTextAcronym = activityMainBinding.editTextAcronym
        editTextAcronym.text.toString()
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.onStart(this)
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.onStop(this)
    }
}


package com.itis.weather.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.itis.weather.R
import com.itis.weather.databinding.ActivityMainBinding
import com.itis.weather.domain.modules.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        navController = findNavController(R.id.navMain)

        navController.setGraph(R.navigation.nav_main)

        binding.toolbar.setupWithNavController(navController)

    }


}
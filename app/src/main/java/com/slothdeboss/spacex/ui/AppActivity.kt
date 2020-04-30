package com.slothdeboss.spacex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.slothdeboss.spacex.R
import kotlinx.android.synthetic.main.activity_main.*

class AppActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.fragment)
        appBottomNav?.setupWithNavController(navController)
        setupNavigationUp()
    }

    private fun setupNavigationUp() {
        NavigationUI.setupActionBarWithNavController(this@AppActivity, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}

package com.gradu.lookthat.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.gradu.lookthat.R
import com.gradu.lookthat.base.BaseActivity
import com.gradu.lookthat.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private var navController : NavController? = null
    override fun initView() {
        initBottomNavigation()
    }
    private fun initBottomNavigation() {
        navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()
        binding.bottomNavView.setupWithNavController(navController!!)
    }
}
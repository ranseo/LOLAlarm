package com.ranseo.lolalarm.alarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ranseo.lolalarm.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.alarm_nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.alarm_bot_navi).setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()

    }
}
package com.ranseo.lolalarm.alarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ranseo.lolalarm.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
    }
}
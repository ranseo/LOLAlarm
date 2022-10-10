package com.ranseo.lolalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ranseo.lolalarm.alarm.AlarmActivity

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent != null && context !=null) {
            Intent(context, AlarmActivity::class.java).also {
                context.startActivity(it)
            }

        }
    }
}
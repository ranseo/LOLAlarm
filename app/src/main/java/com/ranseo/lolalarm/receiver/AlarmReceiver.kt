package com.ranseo.lolalarm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.ranseo.lolalarm.alarm.AlarmActivity
import com.ranseo.lolalarm.util.LogType
import com.ranseo.lolalarm.util.log


const val ACTION_ALARM = "com.ranseo.intent.action.ACTION_ALARM"

private val TAG = "AlarmReceiver"
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        log(TAG,"시작", LogType.I)
        if(intent != null && context !=null) {
            Intent(context, AlarmActivity::class.java).also {
                it.flags = FLAG_ACTIVITY_NEW_TASK
                context.startActivity(it)
            }
        }
    }
}
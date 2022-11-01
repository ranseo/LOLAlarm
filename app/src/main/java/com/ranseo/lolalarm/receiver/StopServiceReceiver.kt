package com.ranseo.lolalarm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

fun BroadcastReceiver.goCoroutineAsync(context:CoroutineContext, task : suspend ()->Unit) {
    val job = SupervisorJob()
    val pendingResult = goAsync()
    CoroutineScope(job).launch(context) {
        try {
            task()
        } finally {
            pendingResult.finish()
            this.cancel()
        }
    }
}

class StopServiceReceiver : BroadcastReceiver() {
    private val TAG= "StopServiceReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        goCoroutineAsync(Dispatchers.IO) {
            delay(15000)
            Log.i(TAG,"onReceive() : after delay(15000)")
        }
    }
}


package com.ranseo.lolalarm

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.ranseo.lolalarm.data.GameInfo
import com.ranseo.lolalarm.data.Spectator
import com.ranseo.lolalarm.data.datasource.MonitorDataSource
import com.ranseo.lolalarm.network.LOLApiService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MonitorService : LifecycleService() {

    @Inject
    lateinit var monitorDataSource: MonitorDataSource

    override fun onCreate() {
        super.onCreate()
    }



    @CallSuper
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if (intent != null) {

            startMonitor(intent)
            stopMonitor()

        }
        return START_NOT_STICKY
    }

    @CallSuper
    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)

        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    private fun stopMonitor() {
        stopSelf()
    }

    private fun startMonitor(intent: Intent) {
        lifecycleScope.launch(Dispatchers.IO) {
            val summonerName = intent.data.toString()
            monitorDataSource.moniterTargetPlayer(summonerName) { spectator ->
                if (spectator != null) {
                    notifyForUser()
                }
            }
        }
    }

    private fun insertGameInfo(spectator: Spectator) {
        lifecycleScope.launch(Dispatchers.IO) {
            val gameInfo = GameInfo(spectator.gameId, spectator, )
        }
    }

    private fun notifyForUser() {

    }
}
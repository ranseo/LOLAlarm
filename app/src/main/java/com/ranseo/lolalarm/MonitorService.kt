package com.ranseo.lolalarm

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.CallSuper
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.ranseo.lolalarm.alarm.AlarmActivity
import com.ranseo.lolalarm.data.GameInfo
import com.ranseo.lolalarm.data.Spectator
import com.ranseo.lolalarm.data.datasource.MonitorDataSource
import com.ranseo.lolalarm.network.LOLApiService
import com.ranseo.lolalarm.util.DateTime
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
                    val date = DateTime.getNowDate()
                    insertGameInfo(spectator, date)
                    notifyForUser(summonerName, date)
                }
            }
        }
    }

    private fun insertGameInfo(spectator: Spectator, date: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val gameInfo = GameInfo(spectator.gameId, spectator, date)
            monitorDataSource.insertGameInfo(gameInfo)
        }
    }

    private fun notifyForUser(summonerName: String, date: String) {
        val notificationChannelId = "게임 접속 알림 서비스 채널"


        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            notificationChannelId,
            "게임 접속 알림",
            NotificationManager.IMPORTANCE_HIGH
        ).let {
            it.description = "게임 접속 알림 서비스"
            it.enableLights(true)
            it.lightColor = Color.RED
            it.enableVibration(true)
            it.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
            it
        }
        notificationManager.createNotificationChannel(channel)


        val pendingIntent = Intent(this, AlarmActivity::class.java).let { intent ->
            PendingIntent.getActivity(this, 0, intent, 0)
        }

        val notification =
            NotificationCompat.Builder(
                this,
                notificationChannelId
            ).setContentTitle("${summonerName} 접속 알림")
                .setContentText("${summonerName} 님이 ${date} 에 접속하였습니다.")
                .setContentIntent(pendingIntent)
                .setTicker("게임 접속 감지!")
                .build()

        notificationManager.notify(0,notification)
    }
}
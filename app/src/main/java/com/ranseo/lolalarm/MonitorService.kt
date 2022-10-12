package com.ranseo.lolalarm

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.CallSuper
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.ranseo.lolalarm.alarm.AlarmActivity
import com.ranseo.lolalarm.data.GameInfo
import com.ranseo.lolalarm.data.ServiceIntentAction
import com.ranseo.lolalarm.data.Spectator
import com.ranseo.lolalarm.data.datasource.MonitorDataSource
import com.ranseo.lolalarm.util.DateTime
import com.ranseo.lolalarm.util.LogType
import com.ranseo.lolalarm.util.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.time.Duration
import javax.inject.Inject

@AndroidEntryPoint
class MonitorService : LifecycleService() {

    private val TAG = "MONITOR_SERVICE"

    @Inject
    lateinit var monitorDataSource: MonitorDataSource

    override fun onCreate() {
        super.onCreate()
        log(TAG, "onCreate()", LogType.I)
    }


    @CallSuper
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        log(
            TAG,
            "onStarteCommand(), intent.Action : ${intent?.action} ,startId : ${startId}",
            LogType.I
        )
        if (intent != null) {
            when (intent.action) {
                ServiceIntentAction.START.name -> startMonitor(intent)
                ServiceIntentAction.STOP.name -> stopMonitor()
            }
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
            while (true) {
                val summonerId =
                    intent.extras?.getString(applicationContext.getString(R.string.extra_key_summoner_id))
                log(TAG, "startMonitor summonerId : ${summonerId}", LogType.I)
                if (summonerId == null) stopSelf()
                else {
                    monitorDataSource.moniterTargetPlayer(summonerId) { spectator ->
                        log(TAG, "startMonitor : ${spectator}", LogType.I)
                        if (spectator != null) {
                            val date = DateTime.getNowDate()
                            insertGameInfo(spectator, date)
                            notifyForUser(summonerId, date)
                        }
                    }
                }

                delay(50000L)
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel(
                notificationChannelId,
                "게임 접속 알림",
                NotificationManager.IMPORTANCE_HIGH
            ).let {
                it.description = "게임 접속 알림 서비스"
                it.enableLights(true)
                it.lightColor = Color.RED
                it.enableVibration(true)
                it.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
                notificationManager.createNotificationChannel(it)
            }
        }

        val pendingIntent = Intent(this, AlarmActivity::class.java).let { intent ->
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        }

        val notification =
            NotificationCompat.Builder(
                this,
                notificationChannelId
            ).setContentTitle("${summonerName} 접속 알림")
                .setContentText("${summonerName} 님이 ${date} 에 접속하였습니다.")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setTicker("게임 접속 감지!")
                .build()

        notificationManager.notify(0, notification)
    }
}
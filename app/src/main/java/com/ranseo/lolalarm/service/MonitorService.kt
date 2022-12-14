package com.ranseo.lolalarm.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.CallSuper
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.ranseo.lolalarm.R
import com.ranseo.lolalarm.alarm.AlarmActivity
import com.ranseo.lolalarm.data.GameInfo
import com.ranseo.lolalarm.data.Spectator
import com.ranseo.lolalarm.data.datasource.MonitorDataSource
import com.ranseo.lolalarm.receiver.StopServiceReceiver
import com.ranseo.lolalarm.util.DateTime
import com.ranseo.lolalarm.util.LogType
import com.ranseo.lolalarm.util.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MonitorService : LifecycleService() {

    private val TAG = "MONITOR_SERVICE"
    private lateinit var hashLoopFlag: HashMap<String, Boolean>
    private var count: Int = 0

    @Inject
    lateinit var monitorDataSource: MonitorDataSource

    override fun onCreate() {
        super.onCreate()
        log(TAG, "onCreate()", LogType.I)
        hashLoopFlag = hashMapOf()
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
                ServiceIntentAction.START.name -> {
                    if(count==0) startForeground(startId, notifyForeground())
                    refreshLastStartId()
                    startMonitor(intent)
                    sendBroadcast()
                }
                ServiceIntentAction.STOP.name -> {
                    count--
                    if (count == 0) {
                        log(TAG, "stopSelfResult : ${startId}",LogType.I)
                        stopSelfResult(startId)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            stopForeground(STOP_FOREGROUND_REMOVE)
                        }

                    } else {
                        stopMonitor(intent)
                    }

                }
            }
        }
        return START_STICKY
    }

    private fun refreshLastStartId() {
        log(TAG,"refreshLastStartId()",LogType.I)
        count++
    }


    @CallSuper
    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)

        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    private fun stopMonitor(intent: Intent) {
        val summonerId =
            intent.extras?.getString(applicationContext.getString(R.string.extra_key_summoner_id))
                ?: return

        log(TAG, "stopMonitor() summonerId : ${summonerId}", LogType.I)

        hashLoopFlag[summonerId] = false
    }

    private fun startMonitor(intent: Intent) {
        val summonerId =
            intent.extras?.getString(applicationContext.getString(R.string.extra_key_summoner_id))
                ?: return

        hashLoopFlag[summonerId] = true

        lifecycleScope.launch(Dispatchers.IO) {

            val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                hashLoopFlag.getOrDefault(summonerId, false)
            } else {
                if(hashLoopFlag.containsKey(summonerId)) hashLoopFlag[summonerId] else false
            }

            while (flag!!) {
                log(TAG, "startMonitor summonerId : ${summonerId}", LogType.I)
                monitorDataSource.moniterTargetPlayer(summonerId) { spectator ->
                    log(TAG, "startMonitor : ${spectator}", LogType.I)
                    if (spectator != null) {
                        val date = DateTime.getNowDate()
                        insertGameInfo(spectator, date, summonerId)
                        notifyForUser(spectator.participants.filter { participant -> participant.summonerId == summonerId }
                            .first().summonerName, date)
                    }
                }
                delay(50_000L)
            }

            this.cancel()
        }
    }

    private fun insertGameInfo(spectator: Spectator, date: String, playerId:String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val gameInfo = GameInfo(spectator.gameId, spectator, date, playerId)
            monitorDataSource.insertGameInfo(gameInfo)
        }
    }


    private fun notifyForUser(summonerName: String, date: String) {
        val notificationChannelId = "?????? ?????? ?????? ????????? ??????"
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel(
                notificationChannelId,
                "?????? ?????? ??????",
                NotificationManager.IMPORTANCE_HIGH
            ).let {
                it.description = "?????? ?????? ?????? ?????????"
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
            ).setContentTitle("${summonerName} ?????? ??????")
                .setContentText("${summonerName} ?????? ${date} ??? ?????????????????????.")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setTicker("?????? ?????? ??????!")
                .build()

        notificationManager.notify(0, notification)
    }

    private fun notifyForeground(): Notification {
        val notificationChannelId = "?????? ?????? ?????? ????????? ?????? ??????"


        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel(
                notificationChannelId,
                "?????? ?????? ?????? ?????? ???",
                NotificationManager.IMPORTANCE_HIGH
            ).let {
                it.description = "?????? ?????? ?????? ?????????"
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
            ).setContentTitle("??? ?????? ?????? ?????????")
                .setContentText("??? ?????? ?????? ???????????? ?????? ??? ?????????.")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()
        return notification
    }

    private fun sendBroadcast() {
        val intent = Intent(this, StopServiceReceiver::class.java)
        sendBroadcast(intent)
    }
}
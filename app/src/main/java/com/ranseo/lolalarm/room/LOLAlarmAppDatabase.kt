package com.ranseo.lolalarm.room

import android.content.Context
import androidx.room.*
import com.ranseo.lolalarm.data.GameInfo
import com.ranseo.lolalarm.data.Spectator
import com.ranseo.lolalarm.data.Summoner
import com.ranseo.lolalarm.data.TargetPlayer

@Database(entities = [TargetPlayer::class, GameInfo::class], version = 6, exportSchema = false)
@TypeConverters(value = [SpectatorConverter::class, SummonerConverter::class])
abstract class LOLAlarmAppDatabase : RoomDatabase() {
    abstract fun alarmDao() : AlarmDAO
}
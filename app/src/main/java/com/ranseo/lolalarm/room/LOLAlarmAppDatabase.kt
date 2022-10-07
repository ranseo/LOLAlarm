package com.ranseo.lolalarm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ranseo.lolalarm.data.Summoner
import com.ranseo.lolalarm.data.TargetPlayer

@Database(entities = [TargetPlayer::class, Summoner::class], version = 1, exportSchema = false)
abstract class LOLAlarmAppDatabase : RoomDatabase() {
    abstract val alarmDao : AlarmDAO
    abstract val searchDao : SearchDAO

}
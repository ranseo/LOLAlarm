package com.ranseo.lolalarm.di

import android.content.Context
import androidx.room.Room
import com.ranseo.lolalarm.room.AlarmDAO
import com.ranseo.lolalarm.room.LOLAlarmAppDatabase
import com.ranseo.lolalarm.room.SearchDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LOLAlarmAppDatabase {
        return Room.databaseBuilder(
            context,
            LOLAlarmAppDatabase::class.java,
            "lol_alarm_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAlarmDao(database: LOLAlarmAppDatabase): AlarmDAO = database.alarmDao()

    @Provides
    fun provideSearchDao(database: LOLAlarmAppDatabase): SearchDAO = database.searchDao()
}
package com.ranseo.lolalarm.di

import android.content.Context
import androidx.room.Room
import com.ranseo.lolalarm.data.Spectator
import com.ranseo.lolalarm.room.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {
//
//    @Provides
//    fun providesSummonerConverter(moshi:Moshi) : SummonerConverter = SummonerConverter(moshi)
//
//    @Provides
//    fun providesSpectatorConverter(moshi:Moshi) : SpectatorConverter = SpectatorConverter(moshi)
//
//    @Provides
//    fun providesListCurrentGameParticipantConverter(moshi:Moshi) : ListCurrentGameParticipantConverter = ListCurrentGameParticipantConverter(moshi)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context, moshi: Moshi)
    : LOLAlarmAppDatabase {
        return Room.databaseBuilder(
            context,
            LOLAlarmAppDatabase::class.java,
            "lol_alarm_database"
        )
            .addTypeConverter(SummonerConverter(moshi))
            .addTypeConverter(SpectatorConverter(moshi))
            .addTypeConverter(ListCurrentGameParticipantConverter(moshi))
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAlarmDao(database: LOLAlarmAppDatabase): AlarmDAO = database.alarmDao()
}
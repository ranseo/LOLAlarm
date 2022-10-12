package com.ranseo.lolalarm.di

import android.content.Context
import androidx.room.Room
import com.ranseo.lolalarm.room.*
import com.squareup.moshi.Moshi
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
    fun provideDatabase(
        @ApplicationContext context: Context,
        spectatorConverter: SpectatorConverter,
        summonerConverter: SummonerConverter
    ): LOLAlarmAppDatabase {
        return Room.databaseBuilder(
            context,
            LOLAlarmAppDatabase::class.java,
            "lol_alarm_database"
        )
            .fallbackToDestructiveMigration()
            .addTypeConverter(spectatorConverter)
            .addTypeConverter(summonerConverter)
            .build()
    }

    @Provides
    fun provideAlarmDao(database: LOLAlarmAppDatabase): AlarmDAO = database.alarmDao()

    @Provides
    @Singleton
    fun providesSummonerConverter(moshi: Moshi): SummonerConverter = SummonerConverter(moshi)

    @Provides
    @Singleton
    fun providesSpectatorConverter(moshi: Moshi): SpectatorConverter = SpectatorConverter(moshi)

//    @Provides
//    @Singleton
//    fun providesParticipantsConverter(moshi: Moshi): ParticipantsConverter =
//        ParticipantsConverter(moshi)

}
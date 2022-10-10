package com.ranseo.lolalarm.repo

import androidx.lifecycle.LiveData
import com.ranseo.lolalarm.data.datasource.AlarmDataSource
import com.ranseo.lolalarm.data.TargetPlayer
import javax.inject.Inject

class AlarmRepositery @Inject constructor(
    private val alarmDataSource: AlarmDataSource,

    ) {
    val targetPlayers : LiveData<List<TargetPlayer>> = alarmDataSource.getAll()

    suspend fun deleteAll() = alarmDataSource.deleteAll()
}
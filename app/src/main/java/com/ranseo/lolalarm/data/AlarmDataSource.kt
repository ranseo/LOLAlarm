package com.ranseo.lolalarm.data

import androidx.lifecycle.LiveData
import com.ranseo.lolalarm.room.AlarmDAO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmDataSource @Inject constructor(private val alarmDAO: AlarmDAO) {
    fun getAll() : LiveData<List<TargetPlayer>> = alarmDAO.getAll()
}
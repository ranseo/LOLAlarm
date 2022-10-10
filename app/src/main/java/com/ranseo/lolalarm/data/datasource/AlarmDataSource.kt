package com.ranseo.lolalarm.data.datasource

import androidx.lifecycle.LiveData
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.room.AlarmDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmDataSource @Inject constructor(private val alarmDAO: AlarmDAO) {
    fun getAll() : LiveData<List<TargetPlayer>> = alarmDAO.getAll()

    suspend fun deleteAll() = withContext(Dispatchers.IO){alarmDAO.deleteAllPlayer()}
}
package com.ranseo.lolalarm.repo

import androidx.lifecycle.LiveData
import com.ranseo.lolalarm.data.AlarmDataSource
import com.ranseo.lolalarm.data.TargetPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class AlarmRepositery @Inject constructor(
    private val alarmDataSource: AlarmDataSource,

) {
    val targetPlayers : LiveData<List<TargetPlayer>> = alarmDataSource.getAll()
}
package com.ranseo.lolalarm.alarm.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.ranseo.lolalarm.LOLApplication
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.repo.AlarmRepositery
import com.ranseo.lolalarm.service.getMonitorServiceState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(private val alarmRepositery: AlarmRepositery, application: Application): AndroidViewModel(application) {

    val lists : LiveData<List<TargetPlayer>> = alarmRepositery.targetPlayers

    val emptyList = Transformations.map(lists) {
        it.isNullOrEmpty()
    }

    init {

    }

    fun deleteAll() {
        viewModelScope.launch {
            alarmRepositery.deleteAll()
        }
    }

    fun getServiceState(summonrName: String) : Boolean {
        return getMonitorServiceState(getApplication(), summonrName)
    }
}
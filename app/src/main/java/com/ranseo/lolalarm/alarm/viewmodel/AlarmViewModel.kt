package com.ranseo.lolalarm.alarm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.repo.AlarmRepositery
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(private val alarmRepositery: AlarmRepositery): ViewModel() {

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
}
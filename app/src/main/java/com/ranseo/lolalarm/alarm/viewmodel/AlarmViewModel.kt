package com.ranseo.lolalarm.alarm.viewmodel

import androidx.lifecycle.ViewModel
import com.ranseo.lolalarm.repo.AlarmRepositery
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(private val alarmRepositery: AlarmRepositery): ViewModel() {

}
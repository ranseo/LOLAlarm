package com.ranseo.lolalarm.alarm.viewmodel

import android.text.Editable
import androidx.lifecycle.*
import com.ranseo.lolalarm.data.Summoner
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.repo.AlarmRepositery
import com.ranseo.lolalarm.repo.HistoryRepositery
import com.ranseo.lolalarm.repo.SearchRepositery
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @AssistedInject constructor(
    historyRepositery: HistoryRepositery,
    @Assisted private val targetPlayer: TargetPlayer
) : ViewModel() {

    val gameInfos = historyRepositery.gameInfos

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(targetPlayer: TargetPlayer) : HistoryViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            targetPlayer: TargetPlayer
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return assistedFactory.create(targetPlayer) as T
            }
        }
    }
}
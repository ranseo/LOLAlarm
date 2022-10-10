package com.ranseo.lolalarm.alarm.viewmodel

import android.text.Editable
import androidx.lifecycle.*
import com.ranseo.lolalarm.data.Summoner
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.repo.AlarmRepositery
import com.ranseo.lolalarm.repo.SearchRepositery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepositery: SearchRepositery,
) : ViewModel() {


    private val _summonerName = MutableLiveData<String>()
    val summonerName: LiveData<String>
        get() = _summonerName


    private val _summoners = MutableLiveData<List<Summoner>>()
    val summoners: LiveData<List<Summoner>>
        get() = _summoners

    fun getSummonerList(summonerName: String) {
        viewModelScope.launch {
            _summoners.value = searchRepositery.searchSummoner(summoner = summonerName)
        }
    }

    fun searchSummoner(s: String) {
        _summonerName.value = s
    }

    fun insertTargetPlayer(summoner: Summoner) {
        viewModelScope.launch {
            searchRepositery.insertTargetPlayer(TargetPlayer(targetId = summoner.id, summoner = summoner))
        }
    }
}
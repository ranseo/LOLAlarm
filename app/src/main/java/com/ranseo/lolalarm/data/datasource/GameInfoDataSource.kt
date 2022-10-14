package com.ranseo.lolalarm.data.datasource

import androidx.lifecycle.LiveData
import com.ranseo.lolalarm.data.GameInfo
import com.ranseo.lolalarm.data.TargetPlayerAndGameInfo
import com.ranseo.lolalarm.room.AlarmDAO
import javax.inject.Inject

class GameInfoDataSource @Inject constructor(
    private val alarmDao :AlarmDAO
) {
   fun getTargetPlayersAndGameInfo() : LiveData<List<TargetPlayerAndGameInfo>> = alarmDao.getTargetPlayersWithGameInfoLists()
}
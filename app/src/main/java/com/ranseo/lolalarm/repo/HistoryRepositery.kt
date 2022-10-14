package com.ranseo.lolalarm.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ranseo.lolalarm.data.GameInfo
import com.ranseo.lolalarm.data.datasource.GameInfoDataSource
import javax.inject.Inject

class HistoryRepositery @Inject constructor(
    private val gameInfoDataSource: GameInfoDataSource
) {

    fun getGameInfos(targetPlayerId:String) : LiveData<List<GameInfo>> {
        return Transformations.map(gameInfoDataSource.getTargetPlayersAndGameInfo()) {
            if(!it.isNullOrEmpty()) {
                 it.first { value -> value.targetPlayer.targetId == targetPlayerId }.gameInfoLists
            } else listOf<GameInfo>()
        }
    }
}
package com.ranseo.lolalarm.repo

import com.ranseo.lolalarm.data.datasource.GameInfoDataSource
import javax.inject.Inject

class HistoryRepositery @Inject constructor(
    private val gameInfoDataSource: GameInfoDataSource
) {
    val gameInfos = gameInfoDataSource.getAllGameInfo()
}
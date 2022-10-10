package com.ranseo.lolalarm.repo

import com.ranseo.lolalarm.data.datasource.SearchDataSource
import com.ranseo.lolalarm.data.Summoner
import com.ranseo.lolalarm.data.TargetPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositery @Inject constructor(
    private val searchDataSource: SearchDataSource
) {

    suspend fun insertTargetPlayer(targetPlayer: TargetPlayer) = withContext(Dispatchers.IO){
        searchDataSource.insertTargetPlayer(targetPlayer)
    }

    suspend fun searchSummoner(summoner: String): List<Summoner> = withContext(Dispatchers.IO) {
        searchDataSource.searchTargetPlayer(summoner)
    }
}
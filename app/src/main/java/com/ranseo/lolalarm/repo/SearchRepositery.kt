package com.ranseo.lolalarm.repo

import com.ranseo.lolalarm.data.SearchDataSource
import com.ranseo.lolalarm.data.Summoner
import com.ranseo.lolalarm.data.TargetPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchRepositery @Inject constructor(
    private val searchDataSource: SearchDataSource
) {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun insertTargetPlayer(targetPlayer: TargetPlayer) {
        coroutineScope.launch {
            searchDataSource.insertTargetPlayer(targetPlayer)
        }
    }

    suspend fun searchSummoner(summoner: String): List<Summoner> = withContext(Dispatchers.IO) {
        searchDataSource.searchTargetPlayer(summoner)
    }
}
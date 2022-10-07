package com.ranseo.lolalarm.data

import com.ranseo.lolalarm.room.SearchDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDataSource @Inject constructor(val searchDAO: SearchDAO) {
    suspend fun insertTargetPlayer(targetPlayer: TargetPlayer) {
        withContext(Dispatchers.IO) {
            searchDAO.insertTargetPlayer(targetPlayer)
        }
    }
}
package com.ranseo.lolalarm.data.datasource

import com.ranseo.lolalarm.BuildConfig
import com.ranseo.lolalarm.data.Summoner
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.network.LOLApiService
import com.ranseo.lolalarm.room.AlarmDAO
import com.ranseo.lolalarm.util.LogType
import com.ranseo.lolalarm.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDataSource @Inject constructor(
    private val alarmDAO: AlarmDAO,
    private val lolApiService: LOLApiService
) {
    suspend fun insertTargetPlayer(targetPlayer: TargetPlayer) {
        withContext(Dispatchers.IO) {
            log("SEARCH_DATASOURCE", "InsertTargetPlayer : ${targetPlayer.toString()}", LogType.I)
            alarmDAO.insertTargetPlayer(targetPlayer)
        }
    }

    suspend fun searchTargetPlayer(
        summoner: String,
        api_key: String = BuildConfig.LOL_API_KEY
    ): List<Summoner> = withContext(Dispatchers.IO) {
        try {
            listOf(lolApiService.getSummoner(summoner, api_key))
        } catch (error: Exception) {
            error.printStackTrace()
            listOf()
        } catch (error: NullPointerException) {
            error.printStackTrace()
            listOf()
        } catch (error: RuntimeException) {
            error.printStackTrace()
            listOf()
        }
    }


}



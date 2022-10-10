package com.ranseo.lolalarm.data.datasource

import com.ranseo.lolalarm.data.GameInfo
import com.ranseo.lolalarm.data.Spectator
import com.ranseo.lolalarm.network.LOLApiService
import com.ranseo.lolalarm.room.AlarmDAO
import dagger.hilt.android.scopes.ServiceScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

@ServiceScoped
class MonitorDataSource @Inject constructor(
    private val lolApiService: LOLApiService,
    private val alarmDAO: AlarmDAO
) {

    suspend fun insertGameInfo(gameInfo: GameInfo) = withContext(Dispatchers.IO) {
        alarmDAO.insertGameInfo(gameInfo)
    }

    suspend fun moniterTargetPlayer(summonerName:String, callback:(spectator:Spectator?)->Unit) = withContext(Dispatchers.IO) {
        try {
            val spectator = lolApiService.getSpector(summonerName, api_key = com.ranseo.lolalarm.BuildConfig.LOL_API_KEY)
            callback(spectator)
        } catch (error:Exception) {
            error.printStackTrace()
            callback(null)
        } catch (error:NullPointerException) {
            error.printStackTrace()
            callback(null)
        } catch (error:IOException) {
            error.printStackTrace()
            callback(null)
        }
    }
}
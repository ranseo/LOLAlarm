package com.ranseo.lolalarm.data

import com.ranseo.lolalarm.BuildConfig
import com.ranseo.lolalarm.network.LOLApiService
import com.ranseo.lolalarm.room.SearchDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDataSource @Inject constructor(
    private val searchDAO: SearchDAO,
    private val lolApiService: LOLApiService
) {
    suspend fun insertTargetPlayer(targetPlayer: TargetPlayer) {
        withContext(Dispatchers.IO) {
            searchDAO.insertTargetPlayer(targetPlayer)
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



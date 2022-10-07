package com.ranseo.lolalarm.network

import com.ranseo.lolalarm.data.Summoner
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LOLApiService {
    @GET("summoner/v4/summoners/by-name/{summonerName}")
    suspend fun getSummoner(
        @Path("summonerName") summonerName: String,
        @Query("api_key") api_key : String
    ) : Summoner
}
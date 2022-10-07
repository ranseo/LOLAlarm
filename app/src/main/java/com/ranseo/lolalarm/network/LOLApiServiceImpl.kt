package com.ranseo.lolalarm.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object LOLApiServiceImpl {
    private const val BASE_URL= "https://kr.api.riotgames.com/lol/"
    private val moshiConverter = MoshiConverterFactory.create()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(moshiConverter)
        .build()

    val service : LOLApiService by lazy { retrofit.create(LOLApiService::class.java) }
}
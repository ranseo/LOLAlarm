package com.ranseo.lolalarm.di

import com.ranseo.lolalarm.network.LOLApiService
import com.ranseo.lolalarm.network.NetworkURL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LOLApiServiceModule {

    @Provides
    fun provideBaseUrl() : String = NetworkURL.LOL_API_BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(moshi:Moshi) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(provideBaseUrl())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideLOLApiService(retrofit:Retrofit) : LOLApiService = retrofit.create(LOLApiService::class.java)

}
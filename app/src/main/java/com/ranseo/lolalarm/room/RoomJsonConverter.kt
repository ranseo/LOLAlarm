package com.ranseo.lolalarm.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.ranseo.lolalarm.data.Spectator
import com.ranseo.lolalarm.data.Summoner
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject


@ProvidedTypeConverter
class SummonerConverter @Inject constructor(private val moshi:Moshi) {

    @TypeConverter
    fun fromString(value: String) : Summoner? {
        val adapter : JsonAdapter<Summoner> = moshi.adapter(Summoner::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromSummoner(summoner: Summoner) : String {
        val adapter : JsonAdapter<Summoner> = moshi.adapter(Summoner::class.java)
        return adapter.toJson(summoner)
    }
}

@ProvidedTypeConverter
class SpectatorConverter @Inject constructor(private val moshi:Moshi) {

    @TypeConverter
    fun fromString(value: String) : Spectator? {
        val adapter : JsonAdapter<Spectator> = moshi.adapter(Spectator::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromSummoner(spectator: Spectator) : String {
        val adapter : JsonAdapter<Spectator> = moshi.adapter(Spectator::class.java)
        return adapter.toJson(spectator)
    }
}

//@ProvidedTypeConverter
//class ParticipantsConverter @Inject constructor(private val moshi:Moshi) {
//
//    @TypeConverter
//    fun fromString(value: String) : List<Spectator.CurrentGameParticipant?>? {
//        val listType= Types.newParameterizedType(List::class.java, Spectator.CurrentGameParticipant::class.java)
//        val adapter : JsonAdapter<List<Spectator.CurrentGameParticipant>> = moshi.adapter(listType)
//        return adapter.fromJson(value)
//    }
//
//    @TypeConverter
//    fun fromParticipants(currentGameParticipant: List<Spectator.CurrentGameParticipant>) : String {
//        val listType= Types.newParameterizedType(List::class.java, Spectator.CurrentGameParticipant::class.java)
//        val adapter : JsonAdapter<List<Spectator.CurrentGameParticipant>> = moshi.adapter(listType)
//        return adapter.toJson(currentGameParticipant)
//    }
//}


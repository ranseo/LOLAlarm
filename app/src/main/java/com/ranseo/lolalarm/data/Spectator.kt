package com.ranseo.lolalarm.data

import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Spectator(
    @field:Json(name = "gameId")
    val gameId:Long,
    @field:Json(name = "gameType")
    val gameType: String,
    @field:Json(name = "gameStartTime")
    val gameStartTime: Long,
    @field:Json(name = "gameLength")
    val gameLength:Long,
    @field:Json(name = "gameMode")
    val gameMode:String,
    @field:Json(name = "participants")
    val participants: List<CurrentGameParticipant>
)  {
}
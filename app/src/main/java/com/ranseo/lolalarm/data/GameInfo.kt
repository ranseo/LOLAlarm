package com.ranseo.lolalarm.data

import androidx.room.*
import com.ranseo.lolalarm.room.SpectatorConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "game_info_table")
data class GameInfo (
    //gameInfoId == spectator.gameId
    @PrimaryKey
    @field:Json(name = "gameInfoId")
    val gameInfoId:Long,
    @field:Json(name ="spectator")
    val spectator : Spectator,
    @field:Json(name="timeStamp")
    @ColumnInfo(name = "time_stamp")
    val timeStamp : String
) {

}
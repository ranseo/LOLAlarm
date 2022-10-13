package com.ranseo.lolalarm.data

import android.media.browse.MediaBrowser
import androidx.recyclerview.widget.DiffUtil
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
    companion object {
        private val callback = object: DiffUtil.ItemCallback<GameInfo>() {
            override fun areItemsTheSame(oldItem: GameInfo, newItem: GameInfo) = oldItem.gameInfoId == newItem.gameInfoId

            override fun areContentsTheSame(oldItem: GameInfo, newItem: GameInfo): Boolean = oldItem == newItem
        }
        fun getItemCallback() = callback
    }

}
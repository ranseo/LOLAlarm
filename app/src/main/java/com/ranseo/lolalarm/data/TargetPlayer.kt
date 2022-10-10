package com.ranseo.lolalarm.data

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "target_player_table")
data class TargetPlayer(
    @field:Json(name ="targetId")
    @PrimaryKey val targetId: String,
    @field:Json(name = "summoner")
    @Embedded val summoner: Summoner
) {
    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<TargetPlayer>() {
            override fun areItemsTheSame(oldItem: TargetPlayer, newItem: TargetPlayer): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: TargetPlayer, newItem: TargetPlayer): Boolean = oldItem.targetId == newItem.targetId
        }

        fun getItemCallback() = itemCallback
    }
}

package com.ranseo.lolalarm.data

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "target_player_table")
data class TargetPlayer(
    @field:Json(name ="targetId")
    @PrimaryKey val targetId: String,
    @field:Json(name = "summoner")
    val summoner: Summoner
) : Parcelable {
    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<TargetPlayer>() {
            override fun areItemsTheSame(oldItem: TargetPlayer, newItem: TargetPlayer): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: TargetPlayer, newItem: TargetPlayer): Boolean = oldItem.targetId == newItem.targetId
        }

        fun getItemCallback() = itemCallback
    }
}

package com.ranseo.lolalarm.data

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "target_player_table")
data class TargetPlayer(
    @PrimaryKey(autoGenerate = true) val id : Long,
    val summoner: Summoner
) {
    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<TargetPlayer>() {
            override fun areItemsTheSame(oldItem: TargetPlayer, newItem: TargetPlayer): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: TargetPlayer, newItem: TargetPlayer): Boolean = oldItem.id == newItem.id
        }

        fun getItemCallback() = itemCallback
    }
}

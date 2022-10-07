package com.ranseo.lolalarm.data

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


data class Summoner(
    @Json(name="id")
    val id: String,
    @Json(name="accountId")
    val accountId: String,
    @Json(name="puuid")
    val puuid: String,
    @Json(name="name")
    val name: String,
    @Json(name="profileIconId")
    val profileIconId: Int
) {
    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<Summoner>() {
            override fun areItemsTheSame(oldItem: Summoner, newItem: Summoner): Boolean = oldItem==newItem
            override fun areContentsTheSame(oldItem: Summoner, newItem: Summoner): Boolean = oldItem.id == newItem.id
        }

        fun getItemCallback() : DiffUtil.ItemCallback<Summoner> = itemCallback
    }
}
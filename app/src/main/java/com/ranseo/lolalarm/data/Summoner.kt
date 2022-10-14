package com.ranseo.lolalarm.data

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Summoner(
    @PrimaryKey
    @field:Json(name="id")
    val id: String,
    @field:Json(name="accountId")
    val accountId: String,
    @field:Json(name="puuid")
    val puuid: String,
    @field:Json(name="name")
    val name: String,
    @field:Json(name="profileIconId")
    val profileIconId: Int
) : Parcelable {
    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<Summoner>() {
            override fun areItemsTheSame(oldItem: Summoner, newItem: Summoner): Boolean = oldItem==newItem
            override fun areContentsTheSame(oldItem: Summoner, newItem: Summoner): Boolean = oldItem.id == newItem.id
        }

        fun getItemCallback() : DiffUtil.ItemCallback<Summoner> = itemCallback
    }
}
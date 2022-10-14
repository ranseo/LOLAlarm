package com.ranseo.lolalarm.alarm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranseo.lolalarm.data.GameInfo
import com.ranseo.lolalarm.data.LOLMap
import com.ranseo.lolalarm.data.Spectator
import com.ranseo.lolalarm.databinding.FragmentHistoryBinding
import com.ranseo.lolalarm.databinding.HistoryListItemBinding
import com.ranseo.lolalarm.util.DateTime
import com.ranseo.lolalarm.util.ImageFromUrl
import com.ranseo.lolalarm.util.LogType
import com.ranseo.lolalarm.util.log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class HistoryListAdapter @Inject constructor(val moshi: Moshi, @ApplicationContext val context: Context) :
    ListAdapter<GameInfo, HistoryListAdapter.HistoryViewHolder>(GameInfo.getItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, moshi, context)
    }


    class HistoryViewHolder(val binding: HistoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GameInfo, moshi: Moshi, context: Context) {
            binding.gameInfo = item
            val mapImageUrl = ImageFromUrl.getMiniMapImageUrl(item.spectator.mapId)
            ImageFromUrl.setProfileImageView(mapImageUrl,binding.root, binding.ivMap)
            setGameInfoOnTheMinimap(item.spectator, moshi, context)
        }

        fun setGameInfoOnTheMinimap(item:Spectator, moshi:Moshi, context: Context) {
            val jsonString = context.assets.open("maps.json").reader().readText()
            val listType = Types.newParameterizedType(List::class.java, LOLMap::class.java)
            val adapter : JsonAdapter<List<LOLMap>> = moshi.adapter(listType)
            val maps = adapter.fromJson(jsonString)


            if(maps.isNullOrEmpty()) return
            log("HistoryViewHolder", "mapId : ${item.mapId}", LogType.I)
            val mapInfo = maps.first { maps -> maps.mapId == item.mapId.toInt() }
            binding.tvMap.text = mapInfo.mapName
            binding.tvStartTime.text = DateTime.getNowDate(item.gameStartTime)
            binding.tvGameMode.text = item.gameType

        }

        companion object {
            fun from(parent: ViewGroup): HistoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HistoryListItemBinding.inflate(layoutInflater, parent, false)
                return HistoryViewHolder(binding)
            }
        }
    }


}
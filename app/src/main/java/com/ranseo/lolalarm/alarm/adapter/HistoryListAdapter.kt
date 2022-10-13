package com.ranseo.lolalarm.alarm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranseo.lolalarm.alarm.adapter.click.ClickAlarmItemListener
import com.ranseo.lolalarm.alarm.viewmodel.AlarmViewModel
import com.ranseo.lolalarm.data.GameInfo
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.databinding.AlarmListItemBinding
import com.ranseo.lolalarm.databinding.FragmentHistoryBinding
import com.ranseo.lolalarm.util.ProfileImage
import javax.inject.Inject

class HistoryListAdapter() :
    ListAdapter<GameInfo, HistoryListAdapter.HistoryViewHolder>(GameInfo.getItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class HistoryViewHolder(val binding: FragmentHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GameInfo) {

        }


        companion object {
            fun from(parent: ViewGroup): HistoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentHistoryBinding.inflate(layoutInflater, parent, false)
                return HistoryViewHolder(binding)
            }
        }
    }


}
package com.ranseo.lolalarm.alarm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.databinding.AlarmListItemBinding
import javax.inject.Inject

class AlarmListAdapter @Inject constructor() : ListAdapter<TargetPlayer, AlarmListAdapter.AlarmViewHolder>(TargetPlayer.getItemCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



    class AlarmViewHolder(val binding: AlarmListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:TargetPlayer) {
            binding.targetPlayer = item
        }

        companion object {
            fun from(parent: ViewGroup): AlarmViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlarmListItemBinding.inflate(layoutInflater, parent, false)
                return AlarmViewHolder(binding)
            }
        }
    }


}
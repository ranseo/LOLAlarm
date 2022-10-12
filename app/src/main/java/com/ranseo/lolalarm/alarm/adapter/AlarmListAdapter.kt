package com.ranseo.lolalarm.alarm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranseo.lolalarm.alarm.adapter.click.ClickAlarmItemListener
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.databinding.AlarmListItemBinding
import com.ranseo.lolalarm.util.ProfileImage
import javax.inject.Inject

class AlarmListAdapter(private val clickAlarmItemListener: ClickAlarmItemListener) : ListAdapter<TargetPlayer, AlarmListAdapter.AlarmViewHolder>(TargetPlayer.getItemCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickAlarmItemListener)
    }

    
    class AlarmViewHolder(val binding: AlarmListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:TargetPlayer, onClickListener:ClickAlarmItemListener) {
            binding.targetPlayer = item
            binding.onClickListener = onClickListener
            binding.flag = true
            binding.btnStart.setOnClickListener {
                setBtnOnClickListener(onClickListener,false,item)
            }
            binding.btnStop.setOnClickListener {
                onClickListener.onClickListener(binding.targetPlayer)
                setBtnOnClickListener(onClickListener,true, item)
            }

            ProfileImage.setProfileImageView(ProfileImage.getProfileImageUrl(item.summoner.profileIconId), binding.root, binding.ivProfile)
        }

        private fun setBtnOnClickListener(onClickListener: ClickAlarmItemListener, flag: Boolean, item:TargetPlayer) {
            if(!flag) onClickListener.onClick(item)
            else onClickListener.onClick(null)
            binding.flag = flag
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
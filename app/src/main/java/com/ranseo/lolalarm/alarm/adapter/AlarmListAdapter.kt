package com.ranseo.lolalarm.alarm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranseo.lolalarm.alarm.adapter.click.ClickAlarmItemListener
import com.ranseo.lolalarm.alarm.viewmodel.AlarmViewModel
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.databinding.AlarmListItemBinding
import com.ranseo.lolalarm.util.GetImageFromUrl

class AlarmListAdapter(private val clickAlarmItemListener: ClickAlarmItemListener, private val viewModel: AlarmViewModel) : ListAdapter<TargetPlayer, AlarmListAdapter.AlarmViewHolder>(TargetPlayer.getItemCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder.from(parent, viewModel)
    }


    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickAlarmItemListener)
    }

    
    class AlarmViewHolder(val binding: AlarmListItemBinding, val viewModel: AlarmViewModel) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:TargetPlayer, onClickListener:ClickAlarmItemListener) {
            binding.targetPlayer = item
            binding.onClickListener = onClickListener
            binding.flag = viewModel.getServiceState(summonrName = item.summoner.name)
            binding.btnStart.setOnClickListener {
                setBtnOnClickListener(onClickListener,false,item)
            }
            binding.btnStop.setOnClickListener {
                setBtnOnClickListener(onClickListener,true, item)
            }

            GetImageFromUrl.setProfileImageView(GetImageFromUrl.getProfileImageUrl(item.summoner.profileIconId), binding.root, binding.ivProfile)
        }

        private fun setBtnOnClickListener(onClickListener: ClickAlarmItemListener, flag: Boolean, item:TargetPlayer) {
            onClickListener.onClick(item, flag)
            binding.flag = flag
        }

        companion object {
            fun from(parent: ViewGroup, viewModel: AlarmViewModel): AlarmViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlarmListItemBinding.inflate(layoutInflater, parent, false)
                return AlarmViewHolder(binding, viewModel)
            }
        }
    }


}
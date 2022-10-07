package com.ranseo.lolalarm.alarm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ranseo.lolalarm.alarm.adapter.click.ClickSearchItemListener
import com.ranseo.lolalarm.data.Summoner
import com.ranseo.lolalarm.databinding.SearchListItemBinding
import com.ranseo.lolalarm.util.ProfileImage
import javax.inject.Inject

class SearchListAdapter (
    private val onClickListener: ClickSearchItemListener
) : ListAdapter<Summoner, SearchListAdapter.SearchViewHolder>(Summoner.getItemCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickListener)
    }

    class SearchViewHolder(val binding: SearchListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Summoner, clickListener:ClickSearchItemListener ) {
            binding.summoner = item
            binding.clickListener = clickListener
            ProfileImage.setProfileImageView(ProfileImage.getProfileImageUrl(item.profileIconId), binding.root, binding.ivProfile)
        }

        companion object {
            fun from(parent: ViewGroup): SearchViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SearchListItemBinding.inflate(layoutInflater, parent, false)
                return SearchViewHolder(binding)
            }
        }
    }
}


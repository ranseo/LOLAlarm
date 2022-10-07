package com.ranseo.lolalarm.alarm.adapter.click

import com.ranseo.lolalarm.data.Summoner



class ClickSearchItemListener (val onClickListener:(data:Summoner)->Unit)  {
    fun onClick(data: Summoner) {
        onClickListener(data)
    }

    fun onLongClick(data: Summoner): Boolean {
        onClickListener(data)
        return true
    }
}
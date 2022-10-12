package com.ranseo.lolalarm.alarm.adapter.click

import com.ranseo.lolalarm.data.TargetPlayer
import javax.inject.Inject


class ClickAlarmItemListener @Inject constructor() : ClickListener {
    lateinit var onClickListener : (targetPlayer: TargetPlayer) -> Unit

    override fun <T> onClick(data: T) {
        onClickListener(data as TargetPlayer)
    }

    override fun <T> onLongClick(data: T): Boolean {

        return true
    }

    fun refreshOnClickListener(_onClickListener: (targetPlayer:TargetPlayer)->Unit) {
        onClickListener = _onClickListener
    }
}
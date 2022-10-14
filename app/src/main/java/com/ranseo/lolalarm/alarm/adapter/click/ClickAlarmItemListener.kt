package com.ranseo.lolalarm.alarm.adapter.click

import com.ranseo.lolalarm.data.TargetPlayer
import javax.inject.Inject


class ClickAlarmItemListener constructor(val onClickListener : (targetPlayer: TargetPlayer)->Unit, val onServiceBtnClickListener : (targetPlayer: TargetPlayer, flag: Boolean) -> Unit) : ClickListener {



    override fun <T> onClick(data:T) {
        onClickListener(data as TargetPlayer)
    }

    override fun <T> onLongClick(data: T): Boolean {
        return true
    }

    fun <T> onClick(data1: T, data2: T) {
        onServiceBtnClickListener(data1 as TargetPlayer, data2 as Boolean)
    }
}
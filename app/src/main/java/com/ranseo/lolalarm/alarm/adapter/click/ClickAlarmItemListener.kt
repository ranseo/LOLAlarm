package com.ranseo.lolalarm.alarm.adapter.click

import com.ranseo.lolalarm.data.TargetPlayer
import javax.inject.Inject


class ClickAlarmItemListener constructor(val onClickListener : (targetPlayer: TargetPlayer?) -> Unit) : ClickListener {

    override fun <T> onClick(data: T) {
        onClickListener(data as TargetPlayer)
    }

    override fun <T> onLongClick(data: T): Boolean {
        return true
    }
}
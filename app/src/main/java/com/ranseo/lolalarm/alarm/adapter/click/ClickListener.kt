package com.ranseo.lolalarm.alarm.adapter.click

interface ClickListener {
    fun <T> onClick(data:T)
    fun <T> onLongClick(data:T) : Boolean
}
package com.ranseo.lolalarm.alarm.adapter.click

interface ClickListener {
    fun <T> onClick(data1:T, data2:T)
    fun <T> onLongClick(data:T) : Boolean
}
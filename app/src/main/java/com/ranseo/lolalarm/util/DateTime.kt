package com.ranseo.lolalarm.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTime {
    private val dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.KOREAN)
    private val simpleDateFormat = SimpleDateFormat("yyyy년 MM월 EEE일 HH시:mm분:ss초", Locale.KOREAN)
    fun getNowDate() : String{
        val date = Date()
        return dateFormat.format(date)
    }

    fun getNowDate(epoch: Long) : String {
        val date = Date(epoch)
        return simpleDateFormat.format(date)
    }
}
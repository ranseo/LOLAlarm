package com.ranseo.lolalarm.util

import java.text.DateFormat
import java.util.*

object DateTime {
    private val dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.KOREAN)

    fun getNowDate() : String{
        val date = Date()
        return dateFormat.format(date)
    }
}
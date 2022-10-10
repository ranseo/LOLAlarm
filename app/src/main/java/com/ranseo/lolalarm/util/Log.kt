package com.ranseo.lolalarm.util

import android.util.Log

enum class LogType{
    I,
    D,
    W,
    E
}
fun log(tag:String, msg:String, type:LogType) {
    when(type) {
        LogType.I -> Log.i(tag,msg)
        LogType.D -> Log.d(tag,msg)
        LogType.W -> Log.w(tag,msg)
        LogType.E -> Log.e(tag,msg)
    }
}
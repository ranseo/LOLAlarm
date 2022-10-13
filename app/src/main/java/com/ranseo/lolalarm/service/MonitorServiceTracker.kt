package com.ranseo.lolalarm.service

import android.content.Context
import android.content.SharedPreferences
import com.ranseo.lolalarm.R

enum class ServiceIntentAction {
    START,
    STOP
}

private const val NAME = "MONITOR_SERVICE_KEY"

fun setMonitorServiceState(context: Context, key: String, state: ServiceIntentAction) {
    val sharedPref = getSharedPreferences(context)
    sharedPref.edit().let {
        it.putString(key,state.name)
        it.apply()
    }
}

fun getMonitorServiceState(context: Context, key:String) : Boolean {
    val sharedPref = getSharedPreferences(context)
    val state = sharedPref.getString(key, ServiceIntentAction.STOP.name)
    return when(state) {
        ServiceIntentAction.START.name -> false
        else -> {true}
    }
}

private fun getSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(
        context.getString(R.string.monitor_service_key),
        Context.MODE_PRIVATE
    )
}
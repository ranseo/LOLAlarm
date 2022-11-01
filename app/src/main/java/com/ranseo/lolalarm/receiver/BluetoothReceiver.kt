package com.ranseo.lolalarm.receiver

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext


class BluetoothReceiver : BroadcastReceiver() {
    private val TAG = "BluetoothReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            when (intent.action) {
                BluetoothAdapter.ACTION_STATE_CHANGED -> {Log.i(TAG,"ACTION_STATE_CHANGED")}
                BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED -> {Log.i(TAG,"ACTION_CONNECTION_STATE_CHANGED")}
                BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {Log.i(TAG,"ACTION_DISCOVERY_STARTED")}
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {Log.i(TAG,"ACTION_DISCOVERY_FINISHED")}
                BluetoothDevice.ACTION_ACL_CONNECTED -> {Log.i(TAG,"ACTION_ACL_CONNECTED")}
                BluetoothDevice.ACTION_ACL_DISCONNECTED -> {Log.i(TAG,"ACTION_ACL_DISCONNECTED")}
                BluetoothDevice.ACTION_BOND_STATE_CHANGED -> {Log.i(TAG,"ACTION_BOND_STATE_CHANGED")}
                BluetoothDevice.ACTION_FOUND -> {Log.i(TAG,"ACTION_FOUND")}
                BluetoothDevice.ACTION_PAIRING_REQUEST -> {Log.i(TAG,"ACTION_PAIRING_REQUEST")}
                else -> {}
            }
        }

        goCoroutineAsync(Dispatchers.IO) {
            delay(15000)
            Log.i(TAG, "onReceive() : after delay(15000)")
        }
    }


}


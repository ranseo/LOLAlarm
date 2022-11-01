package com.ranseo.lolalarm.alarm

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ranseo.lolalarm.R
import com.ranseo.lolalarm.receiver.BluetoothReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment

    private lateinit var bluetoothReceiver: BluetoothReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.alarm_nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.alarm_bot_navi).setupWithNavController(navController)


    }

    override fun onStart() {
        super.onStart()
        bluetoothReceiver = BluetoothReceiver()
        val intentFilter = IntentFilter().also {
            with(it) {
                addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
                addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)
                addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
                addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
                addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
                addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
                addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
                addAction(BluetoothDevice.ACTION_FOUND)
                addAction(BluetoothDevice.ACTION_PAIRING_REQUEST)
            }
        }

        registerReceiver(bluetoothReceiver, intentFilter)

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(bluetoothReceiver)
    }
}
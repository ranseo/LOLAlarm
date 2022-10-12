package com.ranseo.lolalarm.alarm.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ranseo.lolalarm.MonitorService
import com.ranseo.lolalarm.R
import com.ranseo.lolalarm.alarm.adapter.AlarmListAdapter
import com.ranseo.lolalarm.alarm.adapter.click.ClickAlarmItemListener
import com.ranseo.lolalarm.alarm.viewmodel.AlarmViewModel
import com.ranseo.lolalarm.data.ServiceIntentAction
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.databinding.FragmentAlarmBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AlarmFragment : Fragment() {

    private val viewModel: AlarmViewModel by viewModels()
    private lateinit var alarmListAdapter : AlarmListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAlarmBinding>(
            inflater,
            R.layout.fragment_alarm,
            container,
            false
        )

        alarmListAdapter = AlarmListAdapter(ClickAlarmItemListener{ targetPlayer ->
            if (targetPlayer == null) {
                stopMonitorService()
            } else {
                startMonitorService(targetPlayer.summoner.name)
            }
        })

        binding.rvAlarm.adapter = alarmListAdapter


        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.lists.observe(viewLifecycleOwner) {
            it?.let { list ->
                alarmListAdapter.submitList(list)
            }
        }

        return binding.root
    }


    fun startMonitorService(summonerName: String) {
        val intent = Intent(this.context, MonitorService::class.java).also {
            it.action = ServiceIntentAction.START.name
            it.putExtra("SUMMONER_NAME", summonerName)
        }

        requireContext().startService(intent)
    }

    fun stopMonitorService() {
        val intent = Intent(this.context, MonitorService::class.java).also {
            it.action = ServiceIntentAction.STOP.name
        }

        requireContext().stopService(intent)
    }


}
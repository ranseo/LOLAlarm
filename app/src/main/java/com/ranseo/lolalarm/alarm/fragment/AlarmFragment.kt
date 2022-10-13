package com.ranseo.lolalarm.alarm.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ranseo.lolalarm.R
import com.ranseo.lolalarm.alarm.adapter.AlarmListAdapter
import com.ranseo.lolalarm.alarm.adapter.click.ClickAlarmItemListener
import com.ranseo.lolalarm.alarm.viewmodel.AlarmViewModel
import com.ranseo.lolalarm.databinding.FragmentAlarmBinding
import com.ranseo.lolalarm.service.MonitorService
import com.ranseo.lolalarm.service.ServiceIntentAction
import com.ranseo.lolalarm.service.getMonitorServiceState
import com.ranseo.lolalarm.service.setMonitorServiceState
import com.ranseo.lolalarm.util.LogType
import com.ranseo.lolalarm.util.log
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AlarmFragment : Fragment() {

    companion object {
        private const val TAG = "ALARM_FRAGMENT"
    }

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

        alarmListAdapter = AlarmListAdapter(ClickAlarmItemListener{ targetPlayer, flag ->
            log(TAG, "clickAlarmItem targetPlayer: ${targetPlayer}", LogType.I)
            if (flag) {
                stopMonitorService(targetPlayer.summoner.id)
                setMonitorServiceState(requireContext(), targetPlayer.summoner.name, ServiceIntentAction.STOP)
            } else {
                startMonitorService(targetPlayer.summoner.id)
                setMonitorServiceState(requireContext(), targetPlayer.summoner.name, ServiceIntentAction.START)
            }
        }, viewModel)

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


    private fun startMonitorService(summonerId: String) {
        val intent = Intent(this.context, MonitorService::class.java).also {
            it.action = ServiceIntentAction.START.name
            it.putExtra(requireContext().getString(R.string.extra_key_summoner_id), summonerId)
        }

        requireContext().startService(intent)
    }

    private fun stopMonitorService(summonerId: String) {
        val intent = Intent(this.context, MonitorService::class.java).also {
            it.action = ServiceIntentAction.STOP.name
            it.putExtra(getString(R.string.extra_key_summoner_id), summonerId)
        }

        requireContext().startService(intent)
    }



}
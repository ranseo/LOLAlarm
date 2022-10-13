package com.ranseo.lolalarm.alarm.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ranseo.lolalarm.R
import com.ranseo.lolalarm.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
    private lateinit var binding : FragmentHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history,container, false)
        return binding.root
    }


}
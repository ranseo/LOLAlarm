package com.ranseo.lolalarm.alarm.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ranseo.lolalarm.R
import com.ranseo.lolalarm.alarm.adapter.HistoryListAdapter
import com.ranseo.lolalarm.alarm.viewmodel.HistoryViewModel
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.databinding.FragmentHistoryBinding
import javax.inject.Inject


class HistoryFragment : Fragment() {
    private lateinit var binding : FragmentHistoryBinding

    private val args by navArgs<>()

    @Inject lateinit var historyListAdapter: HistoryListAdapter
    @Inject lateinit var historyViewModelFactory: HistoryViewModel.AssistedFactory
    private val historyViewModel : HistoryViewModel by viewModels {
        HistoryViewModel.provideFactory(historyViewModelFactory, args)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history,container, false)
        binding.viewModel = historyViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recHistory.adapter = historyListAdapter

        historyViewModel.gameInfos.observe(viewLifecycleOwner) {
            it?.let{ gameInfos ->
                historyListAdapter.submitList(gameInfos)
            }
        }

        return binding.root
    }

}
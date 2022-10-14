package com.ranseo.lolalarm.alarm.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ranseo.lolalarm.R
import com.ranseo.lolalarm.alarm.adapter.HistoryListAdapter
import com.ranseo.lolalarm.alarm.viewmodel.HistoryViewModel
import com.ranseo.lolalarm.data.TargetPlayer
import com.ranseo.lolalarm.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private lateinit var binding : FragmentHistoryBinding

    private val args by navArgs<HistoryFragmentArgs>()

    @Inject lateinit var historyListAdapter: HistoryListAdapter
    @Inject lateinit var historyViewModelFactory: HistoryViewModel.AssistedFactory
    val historyViewModel : HistoryViewModel by viewModels {
        HistoryViewModel.provideFactory(historyViewModelFactory, args.targetPlayer)
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
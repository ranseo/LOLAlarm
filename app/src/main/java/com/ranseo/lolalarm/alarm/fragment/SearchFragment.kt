package com.ranseo.lolalarm.alarm.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ranseo.lolalarm.R
import com.ranseo.lolalarm.alarm.adapter.SearchListAdapter
import com.ranseo.lolalarm.alarm.adapter.click.ClickSearchItemListener
import com.ranseo.lolalarm.alarm.viewmodel.SearchViewModel
import com.ranseo.lolalarm.databinding.FragmentSearchBinding
import com.ranseo.lolalarm.util.LogType
import com.ranseo.lolalarm.util.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.job
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(), View.OnKeyListener {

    private lateinit var binding : FragmentSearchBinding
    private val searchViewModel : SearchViewModel by viewModels()
    lateinit var searchAdapter : SearchListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search ,container, false)
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        searchAdapter = SearchListAdapter(ClickSearchItemListener { summoner ->
            log(TAG, "on Click Item : ${summoner}", LogType.I)
            searchViewModel.insertTargetPlayer(summoner)
        })

        binding.rvSearch.adapter = searchAdapter

        binding.editSearchPlayer.setOnKeyListener(this@SearchFragment)

        searchViewModel.summonerName.observe(viewLifecycleOwner) {
            it?.let{ name ->
                log(TAG, name, LogType.I)
                searchViewModel.getSummonerList(name)
            }
        }
        searchViewModel.summoners.observe(viewLifecycleOwner) {
            it?.let { list ->
                log(TAG, list.toString(), LogType.I)
                searchAdapter.submitList(list)
            }
        }

        return binding.root
    }

    companion object {
        private const val TAG = "SEARCH_FRAGMENT"
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_ENTER) {
            log(TAG, "KeyEvent.KEYCODE_ENTER", LogType.I)
            val summonerName = binding.editSearchPlayer.text.toString()
            searchViewModel.searchSummoner(summonerName)
        }
        return false
    }
}
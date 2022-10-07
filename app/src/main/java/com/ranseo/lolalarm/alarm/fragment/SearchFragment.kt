package com.ranseo.lolalarm.alarm.fragment

import android.os.Bundle
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.job
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel : SearchViewModel by viewModels()
    lateinit var searchAdapter : SearchListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search ,container, false)
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        searchAdapter = SearchListAdapter(ClickSearchItemListener { summoner ->
            searchViewModel.insertTargetPlayer(summoner)
        })
        binding.rvSearch.adapter = searchAdapter

        searchViewModel.summonerName.observe(viewLifecycleOwner) {
            it?.let{ name ->
                searchViewModel.getSummonerList(name)
            }
        }
        searchViewModel.summoners.observe(viewLifecycleOwner) {
            it?.let { list ->
                searchAdapter.submitList(list)
            }
        }

        return binding.root
    }
}
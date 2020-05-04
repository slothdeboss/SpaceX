package com.slothdeboss.spacex.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.data.event.LoadDataById
import com.slothdeboss.spacex.data.model.History
import com.slothdeboss.spacex.data.state.OnError
import com.slothdeboss.spacex.data.state.OnItemFetched
import kotlinx.android.synthetic.main.fragment_history_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryDetailFragment : Fragment() {

    private val viewModel: HistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let { bundle ->
            val id = HistoryDetailFragmentArgs.fromBundle(bundle).historyId
            viewModel.render(LoadDataById(id = id))
        }
        observeState()
    }

    private fun observeState() {
        viewModel.dataState.observe(viewLifecycleOwner) { state ->
            when (state) {
                OnError -> onErrorState()
                is OnItemFetched<*> -> onItemFetched(history = state.data as History)
            }
        }
    }

    private fun onItemFetched(history: History) {
        historyDetailTitle.text = history.title
        historyDetailDate.text = history.eventDate
        historyDetailFlightNumber.text = history.flightNumber
        historyDetailDescription.text = history.details
    }

    private fun onErrorState() {
        Navigation.findNavController(historyDetailTitle).navigate(
            HistoryDetailFragmentDirections.toHistory()
        )
    }
}

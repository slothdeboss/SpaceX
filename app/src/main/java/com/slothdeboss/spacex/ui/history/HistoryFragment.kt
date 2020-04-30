package com.slothdeboss.spacex.ui.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.ui.history.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.history_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private val TAG = HistoryFragment::class.java.simpleName

    private val viewModel: HistoryViewModel by viewModel()
    private val rvAdapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecycler()
        viewModel.fetchHistory()
        observeAllHistory()
        observeHistory()
    }

    private fun observeHistory() {
        viewModel.history.observe(viewLifecycleOwner) {history ->
            history?.let{
                displaySnackMessage(it.title)
            }
        }
    }

    private fun observeAllHistory() {
        viewModel.allHistory.observe(viewLifecycleOwner) { history ->
            rvAdapter.updateHistoryList(newHistory = history)
            displaySnackMessage(message = "Successfully load data")
        }
    }

    private fun setupRecycler() {
        historyRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }
    }

    private fun displaySnackMessage(message: String) {
        val snackbar = Snackbar.make(historyContent, message, Snackbar.LENGTH_SHORT)
        snackbar.anchorView = appBottomNav
        snackbar.show()
    }
}

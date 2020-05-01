package com.slothdeboss.spacex.ui.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar

import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.data.model.History
import com.slothdeboss.spacex.ui.OnCardClicked
import com.slothdeboss.spacex.ui.history.adapter.HistoryAdapter
import com.slothdeboss.spacex.ui.history.state.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_history.*
import kotlinx.android.synthetic.main.history_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment(), OnCardClicked {

    private val TAG = HistoryFragment::class.java.simpleName

    private val viewModel: HistoryViewModel by viewModel()
    private val rvAdapter = HistoryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecycler()
        viewModel.render(LoadAllHistory)
        observeState()
    }

    override fun onClick(itemId: Int) {
        Navigation.findNavController(historyCard).navigate(
            HistoryFragmentDirections.toDetailScreen().setHistoryId(itemId)
        )
    }

    private fun observeState() {
        viewModel.historyState.observe(viewLifecycleOwner) { state ->
            when (state) {
                Loading -> onLoadingState()
                OnError -> onErrorState("Something went wrong!")
                is OnListFetched -> onListFetched(data = state.data)
            }
        }
    }

    private fun onListFetched(data: List<History>) {
        historyRecycler.visibility = View.VISIBLE
        historyProgressBar.visibility = View.GONE
        rvAdapter.updateHistoryList(newHistory = data)
    }


    private fun onLoadingState() {
        historyRecycler.visibility = View.GONE
        historyProgressBar.visibility = View.VISIBLE
    }

    private fun onErrorState(message: String) {
        Snackbar.make(historyContent, message, Snackbar.LENGTH_LONG)
            .setAnchorView(appBottomNav)
            .setAction("Reload") {
                viewModel.render(LoadAllHistory)
            }
            .show()
    }

    private fun setupRecycler() {
        historyRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }
    }

}

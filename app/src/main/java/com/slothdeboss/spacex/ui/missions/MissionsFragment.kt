package com.slothdeboss.spacex.ui.missions

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.data.event.LoadAllData
import com.slothdeboss.spacex.data.model.Mission
import com.slothdeboss.spacex.data.state.Loading
import com.slothdeboss.spacex.data.state.OnError
import com.slothdeboss.spacex.data.state.OnItemFetched
import com.slothdeboss.spacex.data.state.OnListFetched
import org.koin.androidx.viewmodel.ext.android.viewModel

class MissionsFragment : Fragment() {

    private val TAG = MissionsFragment::class.java.simpleName

    private val viewModel: MissionsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.missions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.render(event = LoadAllData)
        observeState()
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) {state ->
            when (state) {
                Loading -> onLoadingState()
                OnError -> onErrorState()
                is OnListFetched<*> -> onListFetchedState(data = state.data as List<Mission>)
            }
        }
    }

    private fun onListFetchedState(data: List<Mission>) {
        data.forEach {
            Log.i(TAG, it.name)
        }
    }

    private fun onErrorState() {

    }

    private fun onLoadingState() {

    }

}

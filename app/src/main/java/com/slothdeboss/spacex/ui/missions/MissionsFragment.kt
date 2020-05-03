package com.slothdeboss.spacex.ui.missions

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.data.event.LoadAllData
import com.slothdeboss.spacex.data.model.Mission
import com.slothdeboss.spacex.data.state.Loading
import com.slothdeboss.spacex.data.state.OnError
import com.slothdeboss.spacex.data.state.OnItemFetched
import com.slothdeboss.spacex.data.state.OnListFetched
import com.slothdeboss.spacex.ui.OnCardClicked
import com.slothdeboss.spacex.ui.missions.adapter.MissionsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.missions_fragment.*
import kotlinx.android.synthetic.main.rockets_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MissionsFragment : Fragment(), OnCardClicked {

    private val TAG = MissionsFragment::class.java.simpleName

    private val viewModel: MissionsViewModel by viewModel()
    private val missionAdapter = MissionsAdapter(listener = this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.missions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecycler()
        viewModel.render(event = LoadAllData)
        observeState()
    }

    override fun onClick(itemId: Int) {
        Navigation.findNavController(missionsContent).navigate(
            MissionsFragmentDirections.toDetail().setMissionId(itemId)
        )
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) {state ->
            when (state) {
                Loading -> onLoadingState()
                OnError -> onErrorState(message = "Something went wrong!")
                is OnListFetched<*> -> onListFetchedState(data = state.data as List<Mission>)
            }
        }
    }

    private fun onListFetchedState(data: List<Mission>) {
        missionAdapter.updateMissions(missions = data)
        missionsLoadingBar.visibility = View.GONE
        missionsContent.visibility = View.VISIBLE
    }

    private fun onErrorState(message: String) {
        Snackbar.make(missionsContent, message, Snackbar.LENGTH_LONG)
            .setAnchorView(appBottomNav)
            .setAction("Reload") {
                viewModel.render(LoadAllData)
            }
            .show()
    }

    private fun onLoadingState() {
        missionsContent.visibility = View.GONE
        missionsLoadingBar.visibility = View.VISIBLE
    }

    private fun setupRecycler() {
        missionsContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = missionAdapter
        }
    }
}

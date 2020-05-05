package com.slothdeboss.spacex.ui.missions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.ui.event.LoadDataById
import com.slothdeboss.spacex.data.model.Mission
import com.slothdeboss.spacex.data.state.OnError
import com.slothdeboss.spacex.data.state.OnItemFetched
import kotlinx.android.synthetic.main.fragment_mission_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MissionDetailFragment : Fragment() {

    private val viewModel: MissionsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mission_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let { bundle ->
            val id = MissionDetailFragmentArgs.fromBundle(bundle).missionId
            viewModel.render(event = LoadDataById(id = id))
        }
        observeState()
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                OnError -> onErrorState()
                is OnItemFetched<*> -> onItemFetchedState(data = state.data as Mission)
            }
        }
    }

    private fun onItemFetchedState(data: Mission) {
        missionDetailName.text = data.name
        missionDetailId.text = bindFieldWithData(field = "ID", data = data.missionId)
        missionDetailManufacturers.text = bindFieldWithData(
            field = "Manufacturers", data = data.manufacturers.joinToString()
        )
        missionDetailDescription.text = data.description
    }

    private fun onErrorState() {
        Navigation.findNavController(missionDetailCard).navigate(
            MissionDetailFragmentDirections.toMissions()
        )
    }

    private fun bindFieldWithData(field: String, data: String) = "$field: $data"
}

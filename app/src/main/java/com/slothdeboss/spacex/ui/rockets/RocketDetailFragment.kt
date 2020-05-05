package com.slothdeboss.spacex.ui.rockets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.ui.event.LoadDataById
import com.slothdeboss.spacex.data.model.Rocket
import com.slothdeboss.spacex.data.state.OnError
import com.slothdeboss.spacex.data.state.OnItemFetched
import kotlinx.android.synthetic.main.fragment_rocket_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketDetailFragment : Fragment() {

    private val TAG = RocketDetailFragment::class.java.simpleName

    private val viewModel: RocketsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rocket_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let{ bundle ->
            val rocketId = RocketDetailFragmentArgs.fromBundle(bundle).rocketId
            viewModel.render(LoadDataById(id = rocketId))
        }
        observeState()
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) {state ->
            when (state) {
                OnError -> onError()
                is OnItemFetched<*> -> onItemFetched(data = state.data as Rocket)
            }
        }
    }

    private fun onError() {
        Navigation.findNavController(rocketDetailName).navigate(
            RocketDetailFragmentDirections.toRockets()
        )
    }

    private fun onItemFetched(data: Rocket) {
        rocketDetailName.text = data.rocketName
        rocketDetailSuccess.text = bindFieldWithData(field = "Success", data = data.successRate + "%")
        rocketDetailStatus.text = if (data.active) "Status: Active" else "Status: Inactive"
        rocketDetailStages.text = bindFieldWithData(field = "Stages", data = data.stages)
        rocketDetailBoosters.text = bindFieldWithData(field = "Boosters", data = data.boosters)
        rocketDetailCostPerLaunch.text = bindFieldWithData(field = "Cost per launch", data = data.costPerLaunch)
        rocketDetailCompany.text = bindFieldWithData(field = "Company", data = data.company)
        data.description?.let {
            rocketDetailDescription.text = bindFieldWithData(field = "Description", data = it)
        }
        rocketDetailHeight.text = data.height.toString()
        rocketDetailMass.text = data.mass.toString()
        rocketDetailFirstStage.text = data.firstStage.toString()
        rocketDetailSecondStage.text = data.secondStage.toString()
        rocketDetailEngines.text = data.engines.toString()

    }

    private fun bindFieldWithData(field: String, data: String): String = "$field: $data"
}

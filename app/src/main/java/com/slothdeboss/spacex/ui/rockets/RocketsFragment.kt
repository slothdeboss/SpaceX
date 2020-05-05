package com.slothdeboss.spacex.ui.rockets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.ui.event.LoadAllData
import com.slothdeboss.spacex.data.model.Rocket
import com.slothdeboss.spacex.data.state.Loading
import com.slothdeboss.spacex.data.state.OnError
import com.slothdeboss.spacex.data.state.OnListFetched
import com.slothdeboss.spacex.ui.OnCardClicked
import com.slothdeboss.spacex.ui.rockets.adapter.RocketsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rockets_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketsFragment : Fragment(), OnCardClicked {

    private val TAG = RocketsFragment::class.java.simpleName

    private val viewModel: RocketsViewModel by viewModel()
    private val recyclerAdapter = RocketsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rockets_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecycler()
        viewModel.render(event = LoadAllData)
        observeRockets()
    }

    override fun onClick(itemId: Int) {
        Navigation.findNavController(rocketsRecycler).navigate(
            RocketsFragmentDirections.toDetail().setRocketId(itemId)
        )
    }

    private fun observeRockets() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                Loading -> onLoading()
                OnError -> onError("Something went wrong!")
                is OnListFetched<*> -> onListFetched(data = state.data as List<Rocket>)
            }
        }
    }

    private fun onListFetched(data: List<Rocket>) {
        rocketProgressBar.visibility = View.GONE
        rocketsRecycler.visibility = View.VISIBLE
        recyclerAdapter.updateRockerList(rockets = data)
    }

    private fun onError(message: String) {
        Snackbar.make(rocketsRecycler, message, Snackbar.LENGTH_LONG)
            .setAnchorView(appBottomNav)
            .setAction("Reload") {
                viewModel.render(LoadAllData)
            }
            .show()
    }

    private fun onLoading() {
        rocketsRecycler.visibility = View.GONE
        rocketProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecycler() {
        rocketsRecycler.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }
    }
}

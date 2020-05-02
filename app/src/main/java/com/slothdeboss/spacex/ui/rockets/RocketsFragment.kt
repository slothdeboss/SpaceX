package com.slothdeboss.spacex.ui.rockets

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.slothdeboss.spacex.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketsFragment : Fragment() {

    private val TAG = RocketsFragment::class.java.simpleName

    private val viewModel: RocketsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rockets_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchRockets()
        observeRockets()
    }

    private fun observeRockets() {
        viewModel.rockets.observe(viewLifecycleOwner) { rockets ->
            rockets.forEach {
                Log.i(TAG, it.toString())
            }
        }
    }

}

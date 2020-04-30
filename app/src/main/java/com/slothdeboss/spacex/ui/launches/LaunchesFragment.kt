package com.slothdeboss.spacex.ui.launches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.slothdeboss.spacex.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchesFragment : Fragment() {

    companion object {
        fun newInstance() = LaunchesFragment()
    }

    private val viewModel: LaunchesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launches_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}

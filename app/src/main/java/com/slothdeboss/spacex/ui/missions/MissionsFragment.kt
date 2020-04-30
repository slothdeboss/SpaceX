package com.slothdeboss.spacex.ui.missions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.slothdeboss.spacex.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MissionsFragment : Fragment() {

    companion object {
        fun newInstance() = MissionsFragment()
    }

    private val viewModel: MissionsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.missions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}

package com.slothdeboss.spacex.ui.missions.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.data.model.Mission
import com.slothdeboss.spacex.ui.OnCardClicked
import kotlinx.android.synthetic.main.card_mission.view.*

class MissionsAdapter(
    private val listener: OnCardClicked
): RecyclerView.Adapter<MissionsAdapter.MissionsViewHolder>() {

    private val missionsList = mutableListOf<Mission>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_mission, parent, false)
        return MissionsViewHolder(view = view)
    }

    override fun getItemCount(): Int = missionsList.size

    override fun onBindViewHolder(holder: MissionsViewHolder, position: Int) {
        val mission = missionsList[position]
        holder.bind(mission = mission, listener = listener)
    }

    fun updateMissions(missions: List<Mission>) {
        missionsList.clear()
        missionsList.addAll(missions)
        notifyDataSetChanged()
    }

    inner class MissionsViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(mission: Mission, listener: OnCardClicked) {
            view.missionName.text = mission.name
            view.setOnClickListener {
                listener.onClick(mission.id)
            }
        }
    }
}
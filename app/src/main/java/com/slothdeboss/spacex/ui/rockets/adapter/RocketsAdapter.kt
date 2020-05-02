package com.slothdeboss.spacex.ui.rockets.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.data.model.Rocket
import com.slothdeboss.spacex.ui.OnCardClicked
import kotlinx.android.synthetic.main.card_rocket.view.*

class RocketsAdapter(
    private val clickListener: OnCardClicked
) : RecyclerView.Adapter<RocketsAdapter.RocketsViewHolder>() {

    private val rocketList = mutableListOf<Rocket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_rocket, parent, false)
        return RocketsViewHolder(view = view)
    }

    override fun getItemCount(): Int = rocketList.size

    override fun onBindViewHolder(holder: RocketsViewHolder, position: Int) {
        val rocket = rocketList[position]
        holder.bind(rocket = rocket, clickListener)
    }

    fun updateRockerList(rockets: List<Rocket>) {
        rocketList.clear()
        rocketList.addAll(rockets)
        notifyDataSetChanged()
    }

    inner class RocketsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(rocket: Rocket, clickListener: OnCardClicked) {
            val status = getStatus(rocket.active)
            view.rocketCardTitle.text = rocket.rocketName
            view.rocketCardActiveStatus.text = status
            view.rocketCardSuccessRateBar.progress = rocket.successRate.toInt()
            view.setOnClickListener {
                clickListener.onClick(rocket.id)
            }
        }

        private fun getStatus(status: Boolean): String {
            return if (status) {
                "Status: Active"
            } else {
                "Status: Inactive"
            }
        }
    }

}
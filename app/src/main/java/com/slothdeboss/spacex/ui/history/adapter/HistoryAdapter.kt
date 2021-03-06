package com.slothdeboss.spacex.ui.history.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slothdeboss.spacex.R
import com.slothdeboss.spacex.data.model.History
import com.slothdeboss.spacex.ui.OnCardClicked
import kotlinx.android.synthetic.main.card_history.view.*

class HistoryAdapter(
    private val listener: OnCardClicked
): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val historyList = mutableListOf<History>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_history, parent, false)
        return HistoryViewHolder(view = view)
    }

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.bind(history = item, listener = listener)
    }

    fun updateHistoryList(newHistory: List<History>) {
        historyList.clear()
        historyList.addAll(newHistory)
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(history: History, listener: OnCardClicked) {
            view.historyCardTitle.text = history.title
            view.historyCardEventDate.text = history.eventDate
            view.setOnClickListener {
                listener.onClick(history.id)
            }
        }

    }
}
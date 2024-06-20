package com.msaggik.flights.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.msaggik.common_ui.R
import com.msaggik.flights.databinding.ItemTicketBinding
import com.msaggik.flights.domain.model.Ticket

class TicketsAdapter (private val listTickets: List<Ticket>) : RecyclerView.Adapter<TicketsAdapter.TicketsViewHolder>() {

    private var list = listTickets

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return TicketsViewHolder(ItemTicketBinding.inflate(layoutInspector, parent, false))
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        holder.bind(list[position])
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class TicketsViewHolder(private val binding: ItemTicketBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Ticket) {
            binding.badge.isVisible = model.badge?.let {
                binding.badge.text = it
                true
            } ?: false
            binding.price.text = itemView.resources.getString(R.string.form_price_tickets, model.price)
            binding.timeStart.text = model.departureTime
            binding.timeFinish.text = model.arrivalTime
            binding.airportStart.text = model.departureAirportCode
            binding.airportFinish.text = model.arrivalAirportCode
            binding.informationTrack.text = itemView.resources.getString(R.string.form_time_tickets, model.price)
            if(model.hasTransfer) {
                binding.informationTrack.append(itemView.resources.getString(R.string.is_transfer))
            } else {
                binding.informationTrack.append(itemView.resources.getString(R.string.not_transfer))
            }
        }
    }
}
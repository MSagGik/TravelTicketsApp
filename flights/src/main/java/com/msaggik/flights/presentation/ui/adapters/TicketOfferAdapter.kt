package com.msaggik.flights.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.msaggik.common_ui.R
import com.msaggik.flights.databinding.ItemTicketOfferBinding
import com.msaggik.flights.domain.model.TicketOffer

class TicketOfferAdapter (private val listTicketOffer: List<TicketOffer>) : RecyclerView.Adapter<TicketOfferAdapter.TicketOfferViewHolder>() {

    private var list = listTicketOffer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketOfferViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return TicketOfferViewHolder(ItemTicketOfferBinding.inflate(layoutInspector, parent, false))
    }

    override fun onBindViewHolder(holder: TicketOfferViewHolder, position: Int) {
        holder.bind(list[position])
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class TicketOfferViewHolder(private val binding: ItemTicketOfferBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TicketOffer) {
            binding.airline.text = model.title
            binding.departureTime.text = model.timeRange
            binding.price.text = itemView.resources.getString(R.string.form_price_tickets, model.price)
            val imageResId = when (model.id) {
                1 -> R.drawable.ic_red_circle
                10 -> R.drawable.ic_blue_circle
               else -> R.drawable.ic_white_circle
            }
            binding.image.setImageResource(imageResId)
        }
    }
}
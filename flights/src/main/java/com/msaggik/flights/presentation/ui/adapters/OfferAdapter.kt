package com.msaggik.flights.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.msaggik.common_util.Utils
import com.msaggik.common_ui.R
import com.msaggik.flights.databinding.ItemMusicalRouteBinding
import com.msaggik.flights.domain.model.Offer

class OfferAdapter (
    listOffers: List<Offer>
) : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    private var list = listOffers

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return OfferViewHolder(ItemMusicalRouteBinding.inflate(layoutInspector, parent, false))
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class OfferViewHolder (private val binding: ItemMusicalRouteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (model: Offer) {
            Glide.with(itemView)
                .load(model.posterIdRes)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .transform(RoundedCorners(Utils.doToPx(2f, itemView.context.applicationContext)))
                .into(binding.routeCover)
            binding.routeName.text = model.title
            binding.placeOfArrival.text = model.town
            binding.flightCost.text = itemView.resources.getString(R.string.form_price_popular_places, model.price)
        }
    }
}
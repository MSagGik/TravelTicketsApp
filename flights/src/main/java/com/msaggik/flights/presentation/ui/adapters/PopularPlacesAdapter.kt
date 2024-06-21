package com.msaggik.flights.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.msaggik.common_util.Utils
import com.msaggik.flights.databinding.ItemPopularPlacesBinding
import com.msaggik.flights.domain.model.PopularPlaces

class PopularPlacesAdapter(
    listPopularPlaces: List<PopularPlaces>,
    private val placesClickListener: PlacesClickListener
) : RecyclerView.Adapter<PopularPlacesAdapter.PopularPlacesViewHolder>() {

    private var list = listPopularPlaces

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularPlacesViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return PopularPlacesViewHolder(ItemPopularPlacesBinding.inflate(layoutInspector, parent, false))
    }

    override fun onBindViewHolder(holder: PopularPlacesViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            placesClickListener.onPlaceClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun interface PlacesClickListener {
        fun onPlaceClick(place: PopularPlaces)
    }

    class PopularPlacesViewHolder(private val binding: ItemPopularPlacesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: PopularPlaces) {
            Glide.with(itemView)
                .load(model.posterIdRes)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .transform(RoundedCorners(Utils.doToPx(2f, itemView.context.applicationContext)))
                .into(binding.image)
            binding.town.text = model.town
            binding.description.text = model.description
        }
    }
}
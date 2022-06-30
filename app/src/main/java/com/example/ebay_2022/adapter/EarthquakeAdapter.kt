package com.example.ebay_2022.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ebay_2022.R
import com.example.ebay_2022.data.Earthquake
import com.example.ebay_2022.databinding.NewsItemBinding
import com.example.ebay_2022.ui.MapsActivity

class EarthquakeAdapter(private val listener: OnItemClickListener) : ListAdapter<Earthquake,
        EarthquakeAdapter.EarthquakeViewHolder>(EarthquakeComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            EarthquakeViewHolder {
        val binding = NewsItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return EarthquakeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }

        holder.itemView.setOnClickListener {
            val intentOne = Intent(holder.itemView.context, MapsActivity::class.java)
            intentOne.putExtra("source", currentItem?.src)
            ContextCompat.startActivity(holder.itemView.context, intentOne, null)
        }

    }

    inner class EarthquakeViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(earthquake: Earthquake) {
            binding.apply {
                // property #1
                Glide.with(itemView)
                    .load(R.drawable.earthquake_logo)
                    .into(imageViewLogo)

                // property #2, #3, #4
                textViewCountry.text = earthquake.src
                textViewDepth.text = earthquake.depth.toString()
                textViewMagnitude.text = earthquake.magnitude.toString()
                textViewDate.text = earthquake.datetime
            }
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class EarthquakeComparator :
        DiffUtil.ItemCallback<Earthquake>() {
        override fun areItemsTheSame(oldItem: Earthquake, newItem: Earthquake) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Earthquake, newItem: Earthquake) =
            oldItem == newItem
    }

}


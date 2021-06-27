package com.codingwithjks.tvapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.codingwithjks.tvapp.data.Restaurant
import com.codingwithjks.tvapp.databinding.EachRowBinding

class RestaurantAdapter : ListAdapter<Restaurant, RestaurantAdapter.ResturantViewHolder>(Diff) {

    class ResturantViewHolder(private val binding: EachRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            binding.apply {
                logo.load(restaurant.logo)
                name.text = restaurant.name
                description.text = restaurant.description
            }
        }
    }

    object Diff : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResturantViewHolder {
        return ResturantViewHolder(
            EachRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ResturantViewHolder, position: Int) {
        val restaurant = getItem(position)
        if (restaurant != null) {
            holder.bind(restaurant)
        }
    }
}
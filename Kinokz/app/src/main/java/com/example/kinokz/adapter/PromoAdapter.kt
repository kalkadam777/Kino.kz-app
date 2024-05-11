package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinokz.R
import com.example.kinokz.databinding.PromoItemBinding
import com.example.kinokz.model.PromoSection
import com.example.kinokz.model.Promotion

class PromoAdapter(private val promotions: List<Promotion>) : RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val binding = PromoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PromoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.bind(promotions[position])
    }

    override fun getItemCount(): Int = promotions.size

    class PromoViewHolder(private val binding: PromoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(promotion: Promotion) {
            with(binding) {
                Glide
                    .with(itemView.context)
                    .load(promotion.imageUrl)
                    .into(promoImg)
            }
        }
    }
}

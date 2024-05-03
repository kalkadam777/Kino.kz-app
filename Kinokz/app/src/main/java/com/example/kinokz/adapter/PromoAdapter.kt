package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinokz.R
import com.example.kinokz.model.PromoSection
import com.example.kinokz.model.Promotion

class PromoAdapter(private val promotions: List<Promotion>) : RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.promo_item, parent, false)
        return PromoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.bind(promotions[position])
    }

    override fun getItemCount(): Int = promotions.size

    class PromoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.promo_img)
//        private val textView: TextView = view.findViewById(R.id.promoText)

        fun bind(promotion: Promotion) {
//            textView.text = promotion.description
            Glide.with(itemView.context).load(promotion.imageUrl).into(imageView)
        }
    }
}

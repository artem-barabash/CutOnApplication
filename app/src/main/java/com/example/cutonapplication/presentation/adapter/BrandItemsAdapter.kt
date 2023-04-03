package com.example.cutonapplication.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cutonapplication.R
import com.example.cutonapplication.domain.entities.CatalogBrands

class BrandItemsAdapter(private val dataset: List<Pair<String, CatalogBrands.Brand>>,
                        private val context: Context) : RecyclerView.Adapter<BrandItemsAdapter.BrandItemsViewHolder>(){

    class BrandItemsViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image:ImageView = view.findViewById(R.id.imageBrand)
        val title:TextView = view.findViewById(R.id.textBrandTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandItemsViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.brand_item, parent, false)
        return BrandItemsViewHolder(adapterLayout)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: BrandItemsViewHolder, position: Int) {
        val item = dataset[position]

        if(!item.second.brandImage.isNullOrEmpty()){
            Glide.with(context)
                .load(item.second.brandImage)
                .into(holder.image)
        }
        holder.title.text = item.second.brandName
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
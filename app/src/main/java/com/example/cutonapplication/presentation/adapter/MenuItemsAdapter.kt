package com.example.cutonapplication.presentation.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cutonapplication.R
import com.example.cutonapplication.domain.entities.Menu
import com.example.cutonapplication.presentation.ui.HomeActivity
import com.example.cutonapplication.presentation.ui.fragments_home.CatalogFragment


class MenuItemsAdapter(private val dataset : List<Menu.MenuItem?>,
                       private val context: Context,
                        private val fragmentLink:String,
                        private val fragmentToken: String)
    : RecyclerView.Adapter<MenuItemsAdapter.MenuItemsViewHolder>() {

    class MenuItemsViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image:ImageView = view.findViewById(R.id.imageItem)
        val title:TextView = view.findViewById(R.id.textTitleItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemsViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MenuItemsViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MenuItemsViewHolder, position: Int) {
        val item = dataset[position]
        holder.title.text = item!!.nameItem
        if (item.photo != null) {
            Glide.with(context)
                .load(item.photo)
                .into(holder.image)
        }
        holder.itemView.setOnClickListener {
            replaceFragment()
        }
    }

    private fun replaceFragment() {
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        val transactionFragment = fragmentManager.beginTransaction()

        transactionFragment.setReorderingAllowed(true)
        transactionFragment.addToBackStack(null)

        val fragment = CatalogFragment()
        val bundle = Bundle()
        bundle.putString(HomeActivity.CURRENT_ADDRESS, fragmentLink)
        bundle.putString(HomeActivity.CURRENT_TOKEN, fragmentToken)
        fragment.arguments = bundle


        transactionFragment.replace(R.id.fl_layout, fragment)

        transactionFragment.commit()
    }

    override fun getItemCount(): Int {
       return dataset.size
    }
}
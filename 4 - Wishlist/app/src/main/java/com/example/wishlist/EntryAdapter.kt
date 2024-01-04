package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntryAdapter(private val entries: List<Entry>): RecyclerView.Adapter<EntryAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameTextView:TextView
        val priceTextView:TextView
        val urlTextView:TextView
        init{
            nameTextView=itemView.findViewById(R.id.nameTv)
            priceTextView=itemView.findViewById(R.id.priceTv)
            urlTextView=itemView.findViewById(R.id.urlTv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.entry_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val entry = entries.get(position)
        // Set item views based on views and data model
        holder.nameTextView.text = entry.name
        holder.priceTextView.text = entry.price.toString()
        holder.urlTextView.text = entry.url
    }
}
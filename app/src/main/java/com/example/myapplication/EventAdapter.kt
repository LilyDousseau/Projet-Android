package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class EventAdapter (private val events : ArrayList<Event>,
                    private val onEventClick: (Event) -> Unit) :
    RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(
            R.layout.row_event, parent,
            false)

        return EventViewHolder(row)
    }

    override fun onBindViewHolder(viewholder: EventViewHolder, position: Int) {
        val (id, title, lead_text, date_start, date_end, cover_url, address_name, address_street, latitude, longitude, price_type, isFavorite) = this.events[position]

        // Charger l'image depuis l'URL
        Glide.with(viewholder.imgCover.context) // Utilisez le contexte de l'ImageView
            .load(cover_url)                    // Charger l'URL de l'image
            .error(R.drawable.ic_error)         // image en cas d'erreur
            .into(viewholder.imgCover)

        // Configurer les autres champs
        viewholder.txvTitle.text = title
        viewholder.txvAddress.text = address_name

        // Afficher ou non l'icone favori
        if (isFavorite) {
            viewholder.imgFavorite.setImageResource(R.drawable.ic_star_filled)
            viewholder.imgFavorite.visibility = View.VISIBLE
        } else {
            viewholder.imgFavorite.visibility = View.GONE
        }

        // Détecter le clic sur un événement
        viewholder.itemView.setOnClickListener {
            onEventClick(this.events[position])     // Appel du callback avec l'événement à cette position
        }
    }

    override fun getItemCount(): Int {
        return this.events.size
    }
}

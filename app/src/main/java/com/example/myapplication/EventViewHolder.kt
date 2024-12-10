package com.example.myapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    var imgCover: ImageView = rootView.findViewById(R.id.a2_icon_img)    // Image cover de l'event
    var txvTitle = rootView.findViewById<TextView>(R.id.a2_icon_txv_title)   // Titre de l'event
    var txvAddress = rootView.findViewById<TextView>(R.id.a2_txv_address_name)   // Lieu de l'event
    var imgFavorite: ImageView = rootView.findViewById(R.id.r_icon_fav)     // Mis ou non en favori
}

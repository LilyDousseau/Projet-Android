package com.example.myapplication

import java.io.Serializable

data class Event(
    val id: String,
    val title: String,
    val lead_text: String,
    val date_start: String?,
    val date_end: String?,
    val cover_url: String,
    val address_name: String,
    val address_street: String,
    val latitude: Double,
    val longitude: Double,
    val price_type: String?,
    var isFavorite: Boolean = false
) : Serializable
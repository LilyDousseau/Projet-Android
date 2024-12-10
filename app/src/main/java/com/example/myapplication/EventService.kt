package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface EventService {
    @GET("/events")
    fun getAllEvents(): Call<List<Event>>
}
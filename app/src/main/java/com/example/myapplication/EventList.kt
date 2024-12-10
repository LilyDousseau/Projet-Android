package com.example.myapplication

import java.io.Serializable

class EventList {

    private val mapEvent: MutableMap<String, Event> = mutableMapOf()

    // Ajout dun évènement avec son id comme clé
    fun addEvent(event: Event) {
        mapEvent[event.id] = event
    }

    // Retourne lensemble des évènements de la liste
    fun getAllEvents() : ArrayList<Event> {
        return ArrayList(mapEvent.values.sortedBy { !it.isFavorite })
    }

    operator fun iterator(): Iterator<Event> {
        return mapEvent.values.iterator()
    }
}
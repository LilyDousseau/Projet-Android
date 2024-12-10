package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

const val SERVER_BASE_URL = "https://app-1e608587-c9f5-480b-99ec-e45c317aa5b5.cleverapps.io/"   // URL des events

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private var eventList : EventList = EventList()
    lateinit var fragmentList : EventListFragment
    lateinit var fragmentInfo : AppInfoFragment
    lateinit var btnListeEvent : Button
    lateinit var btnMapEvent : Button
    lateinit var btnInfoApp : Button

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    val eventService = retrofit.create(EventService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuration de la toolbar
        val toolbar: Toolbar = findViewById(R.id.a_main_toolbar)
        setSupportActionBar(toolbar)

        btnListeEvent = findViewById<Button>(R.id.a_main_btn_liste)
        btnListeEvent.setOnClickListener {
            displayEventList()
        }

        btnMapEvent = findViewById<Button>(R.id.a_main_btn_map)
        btnMapEvent.setOnClickListener {
            mapFragment()
        }

        btnInfoApp = findViewById<Button>(R.id.a_main_btn_info)
        btnInfoApp.setOnClickListener {
            infoFragment()
        }

        refresh()
    }

    /**
     * Chargement du menu dans la toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Gérer les clics sur l'élément du menu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                refresh()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Mise à jour et affichage de la liste des événements
     */
    private fun refresh() {
        eventList = EventList()   // Remise à zéro de la liste
        eventService.getAllEvents()
            .enqueue(object : Callback<List<Event>> {
                override fun onResponse(
                    call: Call<List<Event>>,
                    response: Response<List<Event>>
                ) {
                    val allEvents: List<Event>? = response.body()
                    if(allEvents != null) {
                        for (event : Event in allEvents) {
                            eventList.addEvent(event)
                        }
                    }

                    displayEventList()
                }

                override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                    // Affichage d'un message d'erreur à l'utilisateur
                    Toast.makeText(baseContext, "Erreur lors du chargement des événements", Toast.LENGTH_SHORT).show()
                }
            })
    }

    /**
     * Affichage de la liste des événements
     */
    private fun displayEventList() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentList = EventListFragment.newInstance(eventList.getAllEvents()) { event ->
            showEventDetails(event) // Gérer le clic sur l'événement
        }

        fragmentTransaction.replace(R.id.a_main_rootview, fragmentList)

        fragmentTransaction.commit()
    }

    /**
     * Affichage de la map
     */
    private fun mapFragment() {
        val supportMapFragment = SupportMapFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.a_main_rootview, supportMapFragment)
            .commit()

        supportMapFragment.getMapAsync(this)
    }

    /**
     * Ajout des marqueurs des événements une fois que la map est prête
     */
    override fun onMapReady(map: GoogleMap) {
        for (event : Event in eventList) {
            map.addMarker(MarkerOptions()
                .position(LatLng(event.latitude, event.longitude))
                .title(event.title)
                .snippet(event.address_name)
            )
        }

        val paris = LatLng(48.866667, 2.333333)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(paris, 10F))   // Zoom sur Paris
    }

    /**
     * Affichage des informations et détails de l'application
     */
    private fun infoFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentInfo = AppInfoFragment()

        fragmentTransaction.replace(R.id.a_main_rootview, fragmentInfo)

        fragmentTransaction.commit()
    }

    /**
     * Démarrage de l'activité EventDetailsActivity avec l'événement sélectionné
     */
    private fun showEventDetails(event: Event) {
        val intent = Intent(this, EventDetailsActivity::class.java)
        intent.putExtra("EVENT", event)  // Passe l'événement comme extra dans l'intent
        startActivityForResult(intent, 1001) // Code de requête
    }

    /**
     * Gestion du résultat envoyé par l'autre activité
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1001 && resultCode == RESULT_OK) {
            val updatedEvent = data?.getSerializableExtra("UPDATED_EVENT") as? Event
            if(updatedEvent != null) {
                eventList.getAllEvents().find { it.id == updatedEvent.id }?.apply {
                    isFavorite = updatedEvent.isFavorite
                }
            }
            displayEventList()
        }
    }

}
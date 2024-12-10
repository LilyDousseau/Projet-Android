package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class EventDetailsActivity : AppCompatActivity() {
    lateinit var btnFavori : ImageButton
    lateinit var btnRetour : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        // Récupérer l'événement passé via l'Intent
        val event = intent.getSerializableExtra("EVENT") as? Event

        btnFavori = findViewById<ImageButton>(R.id.a2_btn_fav)
        btnRetour = findViewById<Button>(R.id.a2_btn_back)

        if (event != null) {
            affichEventDetails(event)   // Afficher les détails de l'événement

            // Afficher le bon bouton favori
            if (event.isFavorite) {
                btnFavori.setImageResource(R.drawable.ic_star_filled) // Icône d'étoile pleine
            } else {
                btnFavori.setImageResource(R.drawable.ic_star_empty) // Icône d'étoile vide
            }
        } else {
            Toast.makeText(this, "Aucun événement trouvé", Toast.LENGTH_SHORT).show()   // Cas où l'évènement est nul
        }

        btnFavori.setOnClickListener {
            if (event != null) {
                event.isFavorite = !event.isFavorite    // Inverser l'état de isFavorite

                // Mettre à jour l'image en fonction de isFavorite
                if (event.isFavorite) {
                    btnFavori.setImageResource(R.drawable.ic_star_filled) // Icône d'étoile pleine
                } else {
                    btnFavori.setImageResource(R.drawable.ic_star_empty) // Icône d'étoile vide
                }
            }
        }

        btnRetour.setOnClickListener {
            if (event != null) {
                val returnIntent = intent  // Préparer un intent pour renvoyer l'événement mis à jour
                returnIntent.putExtra("UPDATED_EVENT", event)
                setResult(RESULT_OK, returnIntent)
            } else {
                setResult(RESULT_CANCELED) // En cas d'erreur ou d'absence d'événement
            }
            finish() // Terminer l'activité
        }
    }

    /**
     * Méthode pour afficher les détails de l'événement dans l'UI
     */
    private fun affichEventDetails(event: Event) {
        findViewById<TextView>(R.id.a2_icon_txv_title).text = event.title
        findViewById<TextView>(R.id.a2_txv_description).text = event.lead_text
        findViewById<TextView>(R.id.a2_txv_address_name).text = event.address_name
        findViewById<TextView>(R.id.a2_txv_address).text = event.address_street
        if(event.date_start != null && event.date_end != null) {
            findViewById<TextView>(R.id.a2_txv_date_contenu).text = "Du " + event.date_start + " au " + event.date_end
        }
        else {
            findViewById<TextView>(R.id.a2_txv_date_contenu).text = "Non précisées"
        }
        if(event.price_type != null) {
            findViewById<TextView >(R.id.a2_txv_price_contenu).text = event.price_type
        }
        else {
            findViewById<TextView >(R.id.a2_txv_price_contenu).text = "Non précisé"
        }
        findViewById<TextView>(R.id.a2_txv_id_contenu).text = event.id

        // Charger l'image
        val imageView = findViewById<ImageView>(R.id.a2_icon_img)
        Glide.with(this)
            .load(event.cover_url)
            .error(R.drawable.ic_error)
            .into(imageView)
    }
}
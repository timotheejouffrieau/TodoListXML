package com.example.todolistxml

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var compteur = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //Associe l'activité à la vue globale

        val textView: TextView = findViewById(R.id.textView) //Récupère la vue associé au texte
        val button: Button = findViewById(R.id.button) //Récupère la vue associé au bouton

        button.setOnClickListener {//Défini l'action à faire après un click
            compteur++ //Incrémente le compteur
            textView.text = "Vous avez appuyer $compteur fois sur le bouton !" //Change le texte à afficher dans la vue texte en fonction du compteur
            //textView.rotation = (compteur * 10).toFloat() //Fait évoluer la rotation du texte en fonction de la valeur du compteur
        }
    }
}
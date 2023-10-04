package com.example.todolistxml

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val COMPTEUR = "COMPTEUR"
    var compteur = MutableLiveData<Int>(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_main) //Associe l'activité à la vue globale

        val textView: TextView = findViewById(R.id.textView) //Récupère la vue associé au texte
        val button: Button = findViewById(R.id.button) //Récupère la vue associé au bouton

        savedInstanceState?.let { bundle -> //verification si le bundle est null ou non
            compteur.value = bundle.getInt(COMPTEUR) //récupération de la donnée dans le bundle et affectation au compteur
        }

        button.setOnClickListener {//Défini l'action à faire après un click
            compteur.value = compteur.value!! + 1 //Incrémente le compteur
        }

        compteur.observe(this){
            textView.text = "Vous avez appuyer ${compteur.value} fois sur le bouton !" //Change le texte à afficher dans la vue texte en fonction du compteur
            //textView.rotation = (compteur * 10).toFloat() //Fait évoluer la rotation du texte en fonction de la valeur du compteur
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COMPTEUR,compteur.value!!) //Sauvegarde la valeur dans le bundle
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}
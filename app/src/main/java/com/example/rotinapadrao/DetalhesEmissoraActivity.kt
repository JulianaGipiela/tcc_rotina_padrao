package com.example.rotinapadrao

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalhesEmissoraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_emissora)

        val idEmissora = intent.getIntExtra("ID_EMISSORA", -1)
        val nomeEmissora = getNomeEmissoraFromId(idEmissora)
        supportActionBar?.title = nomeEmissora


        val textViewTituloEmissora = findViewById<TextView>(R.id.textViewTituloEmissora)
        textViewTituloEmissora.text = nomeEmissora
    }

    private fun getNomeEmissoraFromId(idEmissora: Int): String {
        return when (idEmissora) {
            1 -> "Emissora 1"
            2 -> "Emissora 2"
            3 -> "Emissora 3"
            4 -> "Emissora 4"
            else -> "Emissora Desconhecida"
        }
    }
}

package com.example.rotinapadrao

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalhesEmissoraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_emissora)

        // Configura o título da atividade com o nome da emissora
        val nomeEmissora = intent.getStringExtra("nomeEmissora")
        supportActionBar?.title = nomeEmissora

        // Configura o nome da emissora no TextView
        val textViewTituloEmissora = findViewById<TextView>(R.id.textViewTituloEmissora)
        textViewTituloEmissora.text = nomeEmissora
    }
}

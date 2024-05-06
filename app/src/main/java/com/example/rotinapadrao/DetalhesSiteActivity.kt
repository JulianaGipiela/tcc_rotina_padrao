package com.example.rotinapadrao


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalhesSiteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_site)

        // Recebe o nome do site e da emissora da Intent
        val nomeSite = intent.getStringExtra("NOME_SITE")
        val nomeEmissora = intent.getStringExtra("NOME_EMISSORA")


        // Define o título da ActionBar como o nome do site
        supportActionBar?.title = nomeSite

        // Encontra os TextViews para exibir o nome do site e da emissora
        val txtNomeSite = findViewById<TextView>(R.id.txtNomeSite)
        val txtNomeEmissora = findViewById<TextView>(R.id.txtNomeEmissora)

        // Define o texto dos TextViews com os nomes recebidos
        txtNomeSite.text = nomeSite
        txtNomeEmissora.text = nomeEmissora

        // Encontra os botões "Última Preventiva" e "Nova Preventiva"
        val btnUltimaPreventiva = findViewById<Button>(R.id.btnUltimaPreventiva)
        val btnNovaPreventiva = findViewById<Button>(R.id.btnNovaPreventiva)

        // Define as ações dos botões, se necessário
        // Aqui você pode definir o que acontecerá quando os botões forem clicados
        btnUltimaPreventiva.setOnClickListener {
            // Implemente a ação do botão "Última Preventiva" aqui
        }

        btnNovaPreventiva.setOnClickListener {
            // Implemente a ação do botão "Nova Preventiva" aqui
        }
    }
}

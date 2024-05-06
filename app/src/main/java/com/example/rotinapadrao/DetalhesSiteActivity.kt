package com.example.rotinapadrao


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DetalhesSiteActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_site)

        // Inicialize o Firebase
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        // Recebe o nome do site e da emissora da Intent
        val nomeSite = intent.getStringExtra("NOME_SITE")
        val nomeEmissora = intent.getStringExtra("NOME_EMISSORA")
        val idEmissora = intent.getIntExtra("ID_EMISSORA", -1)
        val idSite = intent.getStringExtra("ID_SITE")



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
            // Abre a tela da última preventiva
            //val intent = Intent(this, DetalhesPreventivaActivity::class.java)
            // Aqui você pode passar os dados da preventiva correspondente, se necessário
            //startActivity(intent)
        }

        val preventivasRef = database.getReference("emissora/$idEmissora/preventivas")

        preventivasRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("DetalhesSiteActivity", "ID do site: $idSite")
                Log.d("DetalhesSiteActivity", "Caminho preventiva: $preventivasRef")
                Log.d("DetalhesSiteActivity", "Dados do Firebase: $snapshot")
                val temPreventivas = snapshot.children.any { preventivaSnapshot ->
                    preventivaSnapshot.child("site").getValue(String::class.java) == idSite
                }
                Log.d("DetalhesSiteActivity", "temPteventivas: $temPreventivas")
                if (temPreventivas) {
                     btnUltimaPreventiva.isEnabled = true
                    btnUltimaPreventiva.setOnClickListener {
                        // Abrir a tela de detalhes da última preventiva para o site
                    }
                } else {
                    btnUltimaPreventiva.isEnabled = false
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Tratar erro, se necessário
            }
        })

        btnNovaPreventiva.setOnClickListener {
            // Implemente a ação do botão "Nova Preventiva" aqui
        }
    }
}

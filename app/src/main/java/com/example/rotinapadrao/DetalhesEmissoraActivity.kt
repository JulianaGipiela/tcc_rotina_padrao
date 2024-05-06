package com.example.rotinapadrao

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DetalhesEmissoraActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_emissora)

        // Inicialize o Firebase
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        val idEmissora = intent.getIntExtra("ID_EMISSORA", -1)
        val emissoraRef = database.getReference("emissora").child(idEmissora.toString())

        // Adicione um ouvinte de valor para recuperar os dados da emissora do Firebase Realtime Database
        emissoraRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val emissora = snapshot.child("nome").getValue(String::class.java)
                if (emissora != null) {
                    supportActionBar?.title = emissora

                    val textViewTituloEmissora = findViewById<TextView>(R.id.txtEmissoraTitle)
                    textViewTituloEmissora.text = emissora

                    val sitesSnapshot = snapshot.child("sites")
                    val layoutBotoesSites = findViewById<LinearLayout>(R.id.layoutBotoesSites)
                    layoutBotoesSites.removeAllViews()

                    for (siteSnapshot in sitesSnapshot.children) {
                        val siteId = siteSnapshot.key
                        val siteNome = siteSnapshot.getValue(String::class.java)
                        if (siteNome != null && siteId != null) {
                            val button = Button(this@DetalhesEmissoraActivity)
                            button.text = siteNome
                            button.setOnClickListener {
                                // Implemente a ação do botão aqui, se necessário
                            }
                            layoutBotoesSites.addView(button)
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Trate o erro, se necessário
            }
        })
    }
}

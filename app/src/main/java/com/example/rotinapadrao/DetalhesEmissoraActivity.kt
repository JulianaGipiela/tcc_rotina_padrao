package com.example.rotinapadrao

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
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

        val logoutButton = findViewById<ImageButton>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            auth.signOut()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val homeButton = findViewById<ImageButton>(R.id.homeButton)
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

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
                            val buttonLayoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            buttonLayoutParams.setMargins(20, 0, 20, 50)

                            val button = Button(this@DetalhesEmissoraActivity)
                            button.text = siteNome
                            button.setTextColor(Color.parseColor("#0079bf"))
                            button.setBackgroundResource(R.drawable.rounded_button_background)
                            button.layoutParams = buttonLayoutParams
                            button.setOnClickListener {
                                val intent = Intent(this@DetalhesEmissoraActivity, DetalhesSiteActivity::class.java)
                                intent.putExtra("ID_SITE", siteId) // Passa o ID do site para a próxima atividade
                                intent.putExtra("ID_EMISSORA", idEmissora)
                                intent.putExtra("NOME_SITE", siteNome)
                                intent.putExtra("NOME_EMISSORA", emissora)
                                startActivity(intent)
                                finish()
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

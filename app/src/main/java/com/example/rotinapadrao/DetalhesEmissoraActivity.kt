package com.example.rotinapadrao

import android.os.Bundle
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

        // Adicione um ouvinte de valor para recuperar o nome da emissora do Firebase Realtime Database
        emissoraRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val nomeEmissora = snapshot.getValue(String::class.java)
                if (nomeEmissora != null) {
                    supportActionBar?.title = nomeEmissora

                    val textViewTituloEmissora = findViewById<TextView>(R.id.txtEmissoraTitle)
                    textViewTituloEmissora.text = nomeEmissora
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Trate o erro, se necessário
            }
        })
    }
}

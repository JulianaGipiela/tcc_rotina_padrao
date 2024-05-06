package com.example.rotinapadrao

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class NovaPreventivaActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    private lateinit var txtNomeSite: TextView
    private lateinit var txtNomeEmissora: TextView
    private lateinit var edtData: EditText
    private lateinit var edtDescricao: EditText
    private lateinit var edtResponsavel: EditText
    private lateinit var btnSavePreventiva: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_preventiva)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        // Inicializa as views
        txtNomeSite = findViewById(R.id.txtNomeSite)
        txtNomeEmissora = findViewById(R.id.txtNomeEmissora)
        edtData = findViewById(R.id.edtData)
        edtDescricao = findViewById(R.id.edtDescricao)
        edtResponsavel = findViewById(R.id.edtResponsavel)
        btnSavePreventiva = findViewById(R.id.btnSavePreventiva)

        // Obtém os dados do Intent
        val nomeSite = intent.getStringExtra("NOME_SITE")
        val nomeEmissora = intent.getStringExtra("NOME_EMISSORA")

        // Define os textos dos TextViews com os nomes do site e da emissora
        txtNomeSite.text = nomeSite
        txtNomeEmissora.text = nomeEmissora

        // Configura o botão para salvar a nova preventiva
        btnSavePreventiva.setOnClickListener {
            // Obtém os valores dos campos
            val data = edtData.text.toString()
            val descricao = edtDescricao.text.toString()
            val responsavel = edtResponsavel.text.toString()

            val idEmissora = intent.getIntExtra("ID_EMISSORA", -1)
            val idSite = intent.getStringExtra("ID_SITE")


            // Obtém uma referência para o nó "preventivas" da emissora específica
            val preventivasRef = database.getReference("emissora/$idEmissora/preventivas")

// Gere uma chave única para a nova preventiva
            val novaPreventivaKey = preventivasRef.push().key

// Verifica se a chave foi gerada corretamente
            if (novaPreventivaKey != null && !idSite.isNullOrEmpty()) {
                // Cria um objeto Preventiva com os detalhes da preventiva
                val novaPreventiva = Preventiva(
                    data = edtData.text.toString(),
                    descricao = edtDescricao.text.toString(),
                    responsavel = edtResponsavel.text.toString(),
                    site = idSite
                )

                // Salva a nova preventiva no Firebase utilizando a chave gerada
                preventivasRef.child(novaPreventivaKey).setValue(novaPreventiva)
                    .addOnSuccessListener {
                        // Preventiva salva com sucesso
                        Log.d("DetalhesSiteActivity", "Preventiva salva com sucesso!")
                        // Aqui você pode adicionar qualquer lógica adicional, se necessário
                    }
                    .addOnFailureListener { e ->
                        // Falha ao salvar a preventiva
                        Log.e("DetalhesSiteActivity", "Falha ao salvar a preventiva: $e")
                        // Aqui você pode tratar a falha, se necessário
                    }
            } else {
                Log.e("DetalhesSiteActivity", "Falha ao gerar chave para a nova preventiva")
            }

            finish()
            //}
        }
    }
}

package com.example.rotinapadrao

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NovaPreventivaActivity : AppCompatActivity() {

    private lateinit var txtNomeSite: TextView
    private lateinit var txtNomeEmissora: TextView
    private lateinit var edtData: EditText
    private lateinit var edtDescricao: EditText
    private lateinit var edtResponsavel: EditText
    private lateinit var btnSavePreventiva: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_preventiva)

        // Inicializa as views
        txtNomeSite = findViewById(R.id.txtNomeSite)
        txtNomeEmissora = findViewById(R.id.txtNomeEmissora)
        //edtData = findViewById(R.id.edtData)
       // edtDescricao = findViewById(R.id.edtDescricao)
       // edtResponsavel = findViewById(R.id.edtResponsavel)
       // btnSavePreventiva = findViewById(R.id.btnSavePreventiva)

        // Obtém os dados do Intent
        val nomeSite = intent.getStringExtra("NOME_SITE")
        val nomeEmissora = intent.getStringExtra("NOME_EMISSORA")

        // Define os textos dos TextViews com os nomes do site e da emissora
        txtNomeSite.text = nomeSite
        txtNomeEmissora.text = nomeEmissora

        // Configura o botão para salvar a nova preventiva
       // btnSavePreventiva.setOnClickListener {
            // Obtém os valores dos campos
         //   val data = edtData.text.toString()
         //   val descricao = edtDescricao.text.toString()
         //   val responsavel = edtResponsavel.text.toString()

            // Aqui você pode implementar a lógica para salvar os dados da preventiva no Firebase
            // Por exemplo, enviar os dados para o Realtime Database ou Firestore

            // Após salvar os dados, você pode fechar esta activity se necessário
          //  finish()
        //}
    }
}

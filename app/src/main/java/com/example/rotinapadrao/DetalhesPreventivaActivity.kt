package com.example.rotinapadrao

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalhesPreventivaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_preventiva)

        val txtData = findViewById<TextView>(R.id.txtData)
        val txtResponsavel = findViewById<TextView>(R.id.txtResponsavel)
        val txtDescricao = findViewById<TextView>(R.id.txtDescricao)
        val checkboxRealizado = findViewById<CheckBox>(R.id.inspecaoCheck)

        val nomeSite = intent.getStringExtra("NOME_SITE")
        val nomeEmissora = intent.getStringExtra("NOME_EMISSORA")
        val data = intent.getStringExtra("DATA")
        val responsavel = intent.getStringExtra("RESPONSAVEL")
        val descricao = intent.getStringExtra("DESCRICAO")
        val isRealizado = intent.getBooleanExtra("REALIZADO", false)
        Log.d("DetalhesPreventivaActivity", "Valor recuperado do Firebase: $isRealizado")
        val txtNomeSite = findViewById<TextView>(R.id.txtNomeSite)
        val txtNomeEmissora = findViewById<TextView>(R.id.txtNomeEmissora)

        txtNomeSite.text = nomeSite
        txtNomeEmissora.text = nomeEmissora
        txtData.text = "Data: $data"
        txtResponsavel.text = "Responsável: $responsavel"
        txtDescricao.text = "Descrição: $descricao"

        checkboxRealizado.isChecked = isRealizado
        checkboxRealizado.isEnabled = false
    }
}

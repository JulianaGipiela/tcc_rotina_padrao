package com.example.rotinapadrao

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class DetalhesPreventivaActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_preventiva)

        auth = FirebaseAuth.getInstance()

        val logoutButton = findViewById<ImageButton>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            auth.signOut()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val txtData = findViewById<TextView>(R.id.txtData)
        val txtResponsavel = findViewById<TextView>(R.id.txtResponsavel)
        val txtDescricao = findViewById<TextView>(R.id.txtDescricao)
        val checkboxRealizado = findViewById<CheckBox>(R.id.inspecaoCheck)
        val limpezaCheck = findViewById<CheckBox>(R.id.limpezaCheck)
        val limpezaExternaCheck = findViewById<CheckBox>(R.id.limpezaExternaCheck)
        val muroCheck = findViewById<CheckBox>(R.id.muroCheck)
        val abrigoCheck = findViewById<CheckBox>(R.id.abrigoCheck)
        val telhadoCheck = findViewById<CheckBox>(R.id.telhadoCheck)
        val sinalCheck = findViewById<CheckBox>(R.id.sinalCheck)
        val nobreakCheck = findViewById<CheckBox>(R.id.nobreakCheck)
        val tensaobanco = findViewById<TextView>(R.id.tensaobanco)
        val frequencia = findViewById<TextView>(R.id.frequencia)
        val correntef3 = findViewById<TextView>(R.id.correnteF3)
        val correntet = findViewById<TextView>(R.id.correnteT)
        val correntef2 = findViewById<TextView>(R.id.correnteF2)
        val correnten = findViewById<TextView>(R.id.correnteN)
        val correntef1 = findViewById<TextView>(R.id.correnteF1)
        val tensaont = findViewById<TextView>(R.id.tensaont)
        val tensaofn = findViewById<TextView>(R.id.tensaofn)
        val tensaof3f1 = findViewById<TextView>(R.id.tensaof3f1)
        val tensaof2f3 = findViewById<TextView>(R.id.tensaof2f3)
        val tensaof1f2 = findViewById<TextView>(R.id.tensaof1f2)




        val nomeSite = intent.getStringExtra("NOME_SITE")
        val nomeEmissora = intent.getStringExtra("NOME_EMISSORA")
        val data = intent.getStringExtra("DATA")
        val responsavel = intent.getStringExtra("RESPONSAVEL")
        val descricao = intent.getStringExtra("DESCRICAO")
        val isRealizado = intent.getBooleanExtra("INSPECAO", false)
        val nlimpezaCheck = intent.getBooleanExtra("LIMPEZA", false)
        val nlimpezaExternaCheck = intent.getBooleanExtra("LIMPEZAEXTERNA", false)
        val nmuroCheck = intent.getBooleanExtra("MURO", false)
        val nabrigoCheck = intent.getBooleanExtra("ABRIGO", false)
        val ntelhadoCheck = intent.getBooleanExtra("TELHADO", false)
        val nsinalCheck = intent.getBooleanExtra("SINAL", false)
        val nnobreakCheck = intent.getBooleanExtra("NOBREAK", false)
        val ntensaobanco = intent.getStringExtra("TENSAOBANCO")
        val nfrequencia = intent.getStringExtra("FREQUENCIA")
        val ncorrentef3 = intent.getStringExtra("CORRENTEF3")
        val ncorrentet = intent.getStringExtra("CORRENTET")
        val ncorrentef2 = intent.getStringExtra("CORRENTEF2")
        val ncorrenten = intent.getStringExtra("CORRENTEN")
        val ncorrentef1 = intent.getStringExtra("CORRENTEF1")
        val ntensaont = intent.getStringExtra("TENSAONT")
        val ntensaofn = intent.getStringExtra("TENSAOFN")
        val ntensaof3f1 = intent.getStringExtra("TENSAOF3F1")
        val ntensaof2f3 = intent.getStringExtra("TENSAOF2F3")
        val ntensaof1f2 = intent.getStringExtra("TENSAOF1F2")



        val txtNomeSite = findViewById<TextView>(R.id.txtNomeSite)
        val txtNomeEmissora = findViewById<TextView>(R.id.txtNomeEmissora)

        txtNomeSite.text = nomeSite
        txtNomeEmissora.text = nomeEmissora
        txtData.text = data
        txtResponsavel.text = responsavel
        txtDescricao.text = descricao
        tensaobanco.text = ntensaobanco
        frequencia.text = nfrequencia
        correntef3.text = ncorrentef3
        correntet.text = ncorrentet
        correntef2.text = ncorrentef2
        correnten.text = ncorrenten
        correntef1.text = ncorrentef1
        tensaont.text = ntensaont
        tensaofn.text = ntensaofn
        tensaof3f1.text = ntensaof3f1
        tensaof2f3.text = ntensaof2f3
        tensaof1f2.text = ntensaof1f2

        checkboxRealizado.isChecked = isRealizado
        limpezaCheck.isChecked = nlimpezaCheck
        limpezaExternaCheck.isChecked = nlimpezaExternaCheck
        muroCheck.isChecked = nmuroCheck
        abrigoCheck.isChecked = nabrigoCheck
        telhadoCheck.isChecked = ntelhadoCheck
        sinalCheck.isChecked = nsinalCheck
        nobreakCheck.isChecked = nnobreakCheck

        checkboxRealizado.isEnabled = false
        limpezaCheck.isEnabled = false
        limpezaExternaCheck.isEnabled = false
        muroCheck.isEnabled = false
        abrigoCheck.isEnabled = false
        telhadoCheck.isEnabled = false
        sinalCheck.isEnabled = false
        nobreakCheck.isEnabled = false
    }
}

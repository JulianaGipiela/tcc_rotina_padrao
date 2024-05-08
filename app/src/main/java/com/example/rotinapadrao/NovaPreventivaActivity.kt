package com.example.rotinapadrao

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.Locale

class NovaPreventivaActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    private lateinit var txtNomeSite: TextView
    private lateinit var txtNomeEmissora: TextView
    private lateinit var edtDescricao: EditText
    private lateinit var edtResponsavel: EditText
    private lateinit var btnSavePreventiva: Button
    private lateinit var limpezaCheck: CheckBox
    private lateinit var inspecaoCheck: CheckBox
    private lateinit var limpezaExternaCheck: CheckBox
    private lateinit var muroCheck: CheckBox
    private lateinit var abrigoCheck: CheckBox
    private lateinit var telhadoCheck: CheckBox
    private lateinit var sinalCheck: CheckBox
    private lateinit var nobreakCheck: CheckBox
    private lateinit var tensaobanco: EditText
    private lateinit var frequencia: EditText
    private lateinit var correntef3: EditText
    private lateinit var correntet: EditText
    private lateinit var correntef2: EditText
    private lateinit var correnten: EditText
    private lateinit var correntef1: EditText
    private lateinit var tensaont: EditText
    private lateinit var tensaofn: EditText
    private lateinit var tensaof3f1: EditText
    private lateinit var tensaof2f3: EditText
    private lateinit var tensaof1f2: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_preventiva)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        val logoutButton = findViewById<ImageButton>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            auth.signOut()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Inicializa as views
        txtNomeSite = findViewById(R.id.txtNomeSite)
        txtNomeEmissora = findViewById(R.id.txtNomeEmissora)
        edtDescricao = findViewById(R.id.edtDescricao)
        edtResponsavel = findViewById(R.id.edtResponsavel)
        btnSavePreventiva = findViewById(R.id.btnSavePreventiva)
        limpezaCheck = findViewById(R.id.limpezaCheck)
        inspecaoCheck = findViewById(R.id.inspecaoCheck)
        limpezaExternaCheck = findViewById(R.id.limpezaExternaCheck)
        muroCheck = findViewById(R.id.muroCheck)
        abrigoCheck = findViewById(R.id.abrigoCheck)
        telhadoCheck = findViewById(R.id.telhadoCheck)
        sinalCheck = findViewById(R.id.sinalCheck)
        nobreakCheck = findViewById(R.id.nobreakCheck)
        tensaobanco = findViewById(R.id.tensaobanco)
        frequencia = findViewById(R.id.frequencia)
        correntef3 = findViewById(R.id.correnteF3)
        correntet = findViewById(R.id.correnteT)
        correntef2 = findViewById(R.id.correnteF2)
        correnten = findViewById(R.id.correnteN)
        correntef1 = findViewById(R.id.correnteF1)
        tensaont = findViewById(R.id.tensaont)
        tensaofn = findViewById(R.id.tensaofn)
        tensaof3f1 = findViewById(R.id.tensaof3f1)
        tensaof2f3 = findViewById(R.id.tensaof2f3)
        tensaof1f2 = findViewById(R.id.tensaof1f2)

        // Obtém os dados do Intent
        val nomeSite = intent.getStringExtra("NOME_SITE")
        val nomeEmissora = intent.getStringExtra("NOME_EMISSORA")

        // Define os textos dos TextViews com os nomes do site e da emissora
        txtNomeSite.text = nomeSite
        txtNomeEmissora.text = nomeEmissora

        // Configura o botão para salvar a nova preventiva
        btnSavePreventiva.setOnClickListener {
            // Obtém os valores dos campos
            val descricao = edtDescricao.text.toString()
            val responsavel = edtResponsavel.text.toString()

            val idEmissora = intent.getIntExtra("ID_EMISSORA", -1)
            val idSite = intent.getStringExtra("ID_SITE")


            // Obtém uma referência para o nó "preventivas" da emissora específica
            val preventivasRef = database.getReference("emissora/$idEmissora/preventivas")

// Gere uma chave única para a nova preventiva
            val novaPreventivaKey = preventivasRef.push().key
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

// Verifica se a chave foi gerada corretamente
            if (novaPreventivaKey != null && !idSite.isNullOrEmpty()) {
                // Cria um objeto Preventiva com os detalhes da preventiva
                val novaPreventiva = Preventiva(
                    data = dateFormat.format(Calendar.getInstance().time),
                    descricao = edtDescricao.text.toString(),
                    responsavel = edtResponsavel.text.toString(),
                    limpeza = limpezaCheck.isChecked,
                    inspecao = inspecaoCheck.isChecked,
                    limpezaExterna = limpezaExternaCheck.isChecked,
                    muro = muroCheck.isChecked,
                    abrigo = abrigoCheck.isChecked,
                    telhado = telhadoCheck.isChecked,
                    sinal = sinalCheck.isChecked,
                    nobreak = nobreakCheck.isChecked,
                    tensaobanco = tensaobanco.text.toString(),
                    frequencia = frequencia.text.toString(),
                    correntef3 = correntef3.text.toString(),
                    correntet = correntet.text.toString(),
                    correntef2 = correntef2.text.toString(),
                    correnten = correnten.text.toString(),
                    correntef1 = correntef1.text.toString(),
                    tensaont = tensaont.text.toString(),
                    tensaofn = tensaofn.text.toString(),
                    tesaof3f1 = tensaof3f1.text.toString(),
                    tensaof2f3 = tensaof2f3.text.toString(),
                    tensaof1f2 = tensaof1f2.text.toString(),
                    site = idSite
                )

                // Salva a nova preventiva no Firebase utilizando a chave gerada
                preventivasRef.child(novaPreventivaKey).setValue(novaPreventiva)
                    .addOnSuccessListener {
                        Toast.makeText(this@NovaPreventivaActivity, "Preventiva salva com sucesso!", Toast.LENGTH_SHORT).show()
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

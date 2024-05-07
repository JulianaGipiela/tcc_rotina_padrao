package com.example.rotinapadrao


import android.content.Intent
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
                        Log.d("DetalhesSiteActivity", "Clicou no botão Última Preventiva")

                        val query = preventivasRef.orderByChild("data").limitToLast(1)

                        query.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()) {
                                    // Obtém a última preventiva
                                    val ultimaPreventiva = snapshot.children.first()

                                    // Extrai os dados da preventiva
                                    val data = ultimaPreventiva.child("data").getValue(String::class.java)
                                    val responsavel = ultimaPreventiva.child("responsavel").getValue(String::class.java)
                                    val descricao = ultimaPreventiva.child("descricao").getValue(String::class.java)
                                    val inspecao = ultimaPreventiva.child("inspecao").getValue(Boolean::class.java)
                                    val limpeza = ultimaPreventiva.child("limpeza").getValue(Boolean::class.java)
                                    val limpezaExterna = ultimaPreventiva.child("limpezaExterna").getValue(Boolean::class.java)
                                    val muro = ultimaPreventiva.child("muro").getValue(Boolean::class.java)
                                    val abrigo = ultimaPreventiva.child("abrigo").getValue(Boolean::class.java)
                                    val telhado = ultimaPreventiva.child("telhado").getValue(Boolean::class.java)
                                    val sinal = ultimaPreventiva.child("sinal").getValue(Boolean::class.java)
                                    val nobreak = ultimaPreventiva.child("nobreak").getValue(Boolean::class.java)
                                    val tensaobanco = ultimaPreventiva.child("tensaobanco").getValue(String::class.java)
                                    val frequencia = ultimaPreventiva.child("frequencia").getValue(String::class.java)
                                    val correntef3 = ultimaPreventiva.child("correntef3").getValue(String::class.java)
                                    val correntet = ultimaPreventiva.child("correntet").getValue(String::class.java)
                                    val correntef2 = ultimaPreventiva.child("correntef2").getValue(String::class.java)
                                    val correnten = ultimaPreventiva.child("correnten").getValue(String::class.java)
                                    val correntef1 = ultimaPreventiva.child("correntef1").getValue(String::class.java)
                                    val tensaont = ultimaPreventiva.child("tensaont").getValue(String::class.java)
                                    val tensaofn = ultimaPreventiva.child("tensaofn").getValue(String::class.java)
                                    val tensaof3f1 = ultimaPreventiva.child("tesaof3f1").getValue(String::class.java)
                                    val tensaof2f3 = ultimaPreventiva.child("tensaof2f3").getValue(String::class.java)
                                    val tensaof1f2 = ultimaPreventiva.child("tensaof1f2").getValue(String::class.java)
                                    Log.d("DetalhesSiteActivity", "Valor recuperado do Firebase Inspeção: $inspecao")
                                    Log.d("DetalhesSiteActivity", "Valor recuperado do Firebase Limpeza: $limpeza")
                                    Log.d("DetalhesSiteActivity", "Valor recuperado do Firebase Correnten: $correnten")
                                    val intent = Intent(this@DetalhesSiteActivity, DetalhesPreventivaActivity::class.java)
                                    intent.putExtra("NOME_SITE", nomeSite) // Passa o ID do site para a próxima atividade
                                    intent.putExtra("NOME_EMISSORA", nomeEmissora)
                                    intent.putExtra("DATA", data)
                                    intent.putExtra("RESPONSAVEL", responsavel)
                                    intent.putExtra("DESCRICAO", descricao)
                                    intent.putExtra("INSPECAO", inspecao)
                                    intent.putExtra("LIMPEZA", limpeza)
                                    intent.putExtra("LIMPEZAEXTERNA", limpezaExterna)
                                    intent.putExtra("MURO", muro)
                                    intent.putExtra("ABRIGO", abrigo)
                                    intent.putExtra("TELHADO", telhado)
                                    intent.putExtra("SINAL", sinal)
                                    intent.putExtra("NOBREAK", nobreak)
                                    intent.putExtra("TENSAOBANCO", tensaobanco)
                                    intent.putExtra("FREQUENCIA", frequencia)
                                    intent.putExtra("CORRENTEF3", correntef3)
                                    intent.putExtra("CORRENTET", correntet)
                                    intent.putExtra("CORRENTEF2", correntef2)
                                    intent.putExtra("CORRENTEN", correnten)
                                    intent.putExtra("CORRENTEF1", correntef1)
                                    intent.putExtra("TENSAONT", tensaont)
                                    intent.putExtra("TENSAOFN", tensaofn)
                                    intent.putExtra("TENSAOF3F1", tensaof3f1)
                                    intent.putExtra("TENSAOF2F3", tensaof2f3)
                                    intent.putExtra("TENSAOF1F2", tensaof1f2)
                                    startActivity(intent)
                                    // Agora você pode usar esses dados como quiser
                                } else {
                                    // Não há preventivas para este site
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                // Tratar erro, se necessário
                            }
                        })
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
            val intent = Intent(this@DetalhesSiteActivity, NovaPreventivaActivity::class.java)
            intent.putExtra("NOME_SITE", nomeSite)
            intent.putExtra("NOME_EMISSORA", nomeEmissora)
            intent.putExtra("ID_EMISSORA", idEmissora)
            intent.putExtra("ID_SITE", idSite)
            startActivity(intent)
        }
    }
}

package com.example.rotinapadrao

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rotinapadrao.LoginActivity
import com.example.rotinapadrao.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Configuração do clique do botão de logout (ImageButton)
        binding.headerLayout.logoutButton.setOnClickListener {
            // Realiza o logout do usuário
            auth.signOut()

            // Após fazer logout, redirecione o usuário para a tela de login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Fecha a MainActivity após iniciar a LoginActivity
        }

        binding.btnEmissora1.setOnClickListener {
            abrirDetalhesEmissora(1)
        }

        binding.btnEmissora2.setOnClickListener {
            abrirDetalhesEmissora(2)
        }

        binding.btnEmissora3.setOnClickListener {
            abrirDetalhesEmissora(3)
        }

        binding.btnEmissora4.setOnClickListener {
            abrirDetalhesEmissora(4)
        }


    }

    private fun abrirDetalhesEmissora(idEmissora: Int) {
        val intent = Intent(this, DetalhesEmissoraActivity::class.java)
        intent.putExtra("ID_EMISSORA", idEmissora)
        startActivity(intent)
        finish()
    }
}

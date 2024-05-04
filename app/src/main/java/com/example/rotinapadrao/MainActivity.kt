package com.example.rotinapadrao

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rotinapadrao.databinding.ActivityMainBinding
import com.example.rotinapadrao.databinding.HeaderLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var headerBinding: HeaderLayoutBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        headerBinding = HeaderLayoutBinding.bind(binding.root.findViewById(R.id.headerLayout))
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Configuração do clique do botão de logout
        headerBinding.logoutButton.setOnClickListener {
            // Realiza o logout do usuário
            auth.signOut()

            // Após fazer logout, redirecione o usuário para a tela de login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Fecha a MainActivity após iniciar a LoginActivity
        }
    }
}

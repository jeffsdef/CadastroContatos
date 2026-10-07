package com.example.cadastrocontatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cadastrocontatos.data.AppDatabase
import com.example.cadastrocontatos.repository.ContatoRepository
import com.example.cadastrocontatos.ui.theme.ContatoScreen
import com.example.cadastrocontatos.ui.theme.CadastroContatosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = ContatoRepository(database.contatoDao())

        setContent {
            CadastroContatosTheme {

                val contatoViewModel: ContatoViewModel = viewModel(
                    factory = ContatoViewModelFactory(repository)
                )

                ContatoScreen(
                    viewModel = contatoViewModel
                )
            }
        }
    }
}
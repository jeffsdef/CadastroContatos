package com.example.cadastrocontatos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cadastrocontatos.data.Contato
import com.example.cadastrocontatos.repository.ContatoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContatoViewModel(
    private val repository: ContatoRepository
) : ViewModel() {

    val contatos: StateFlow<List<Contato>> =
        repository.contatos.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun inserir(
        nome: String,
        telefone: String,
        email: String
    ) {
        viewModelScope.launch {
            repository.inserir(
                Contato(
                    nome = nome,
                    telefone = telefone,
                    email = email
                )
            )
        }
    }

    fun excluir(contato: Contato) {
        viewModelScope.launch {
            repository.excluir(contato)
        }
    }
}

class ContatoViewModelFactory(
    private val repository: ContatoRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContatoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContatoViewModel(repository) as T
        }

        throw IllegalArgumentException("ViewModel desconhecido")
    }
}
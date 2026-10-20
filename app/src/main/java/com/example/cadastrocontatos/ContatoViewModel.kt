package com.example.cadastrocontatos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cadastrocontatos.api.Endereco
import com.example.cadastrocontatos.api.RetrofitClient
import com.example.cadastrocontatos.data.Contato
import com.example.cadastrocontatos.repository.ContatoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _endereco = MutableStateFlow<Endereco?>(null)
    val endereco = _endereco.asStateFlow()

    private val _erroCep = MutableStateFlow<String?>(null)
    val erroCep = _erroCep.asStateFlow()

    fun inserir(nome: String, telefone: String, email: String) {
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

    fun consultarCep(cep: String) {
        val cepLimpo = cep.filter { it.isDigit() }

        if (cepLimpo.length != 8) {
            _endereco.value = null
            _erroCep.value = "Digite um CEP com 8 números."
            return
        }

        viewModelScope.launch {
            _endereco.value = null
            _erroCep.value = null

            try {
                _endereco.value =
                    RetrofitClient.service.consultarCep(cepLimpo)
            } catch (e: Exception) {
                _erroCep.value =
                    "Não foi possível consultar o CEP. Verifique o número e a conexão."
            }
        }
    }
}

class ContatoViewModelFactory(
    private val repository: ContatoRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(ContatoViewModel::class.java)) {
            return ContatoViewModel(repository) as T
        }

        throw IllegalArgumentException(
            "ViewModel desconhecido: ${modelClass.name}"
        )
    }
}
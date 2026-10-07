package com.example.cadastrocontatos.repository

import com.example.cadastrocontatos.data.Contato
import com.example.cadastrocontatos.data.ContatoDao
import kotlinx.coroutines.flow.Flow

class ContatoRepository(
    private val contatoDao: ContatoDao
) {

    val contatos: Flow<List<Contato>> = contatoDao.listar()

    suspend fun inserir(contato: Contato) {
        contatoDao.inserir(contato)
    }

    suspend fun excluir(contato: Contato) {
        contatoDao.excluir(contato)
    }
}
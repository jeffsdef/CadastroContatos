package com.example.cadastrocontatos.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContatoDao {

    @Insert
    suspend fun inserir(contato: Contato)

    @Query("SELECT * FROM contatos ORDER BY nome")
    fun listar(): Flow<List<Contato>>

    @Delete
    suspend fun excluir(contato: Contato)
}
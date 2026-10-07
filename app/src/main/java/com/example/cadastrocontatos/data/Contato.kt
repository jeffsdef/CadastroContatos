package com.example.cadastrocontatos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contatos")
data class Contato(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val telefone: String,
    val email: String
)

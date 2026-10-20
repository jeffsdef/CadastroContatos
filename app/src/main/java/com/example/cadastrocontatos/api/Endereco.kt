package com.example.cadastrocontatos.api

data class Endereco(
    val cep: String,
    val state: String,
    val city: String,
    val neighborhood: String,
    val street: String
)
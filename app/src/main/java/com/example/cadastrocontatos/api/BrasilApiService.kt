package com.example.cadastrocontatos.api

import retrofit2.http.GET
import retrofit2.http.Path

interface BrasilApiService {

    @GET("cep/v2/{cep}")
    suspend fun consultarCep(
        @Path("cep") cep: String
    ): Endereco
}
package org.proconsi.multiplatform.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.proconsi.multiplatform.data.remote.dto.ApiResponse
import org.proconsi.multiplatform.data.remote.dto.Lugar
import org.proconsi.multiplatform.data.createHttpClient

class LugarRepo {
    private val httpClient = createHttpClient()
    private val apiUrl = "https://tuciudaddecerca-api.proconsi.com/Categoria"

    suspend fun getLugares(): List<Lugar> {
        return try {
            val response = httpClient.get(apiUrl) {
                parameter("idCategoriaPadre", 30)
                parameter("idIdioma", 0)
                parameter("idProyecto", 1)
            }.body<ApiResponse>()
            println("LugarRepo: ÉXITO. Se recibieron ${response.fichas.size} lugares.")
            return response.fichas
        } catch (e: Exception) {
            println("Error en ApiServiceImpl.getElements: ${e.message}")
            e.printStackTrace()
            emptyList()
        }
    }
}

package org.proconsi.multiplatform.data

import io.ktor.client.call.*
import io.ktor.client.request.*

class LugarRepo {
    private val apiUrl = "https://tuciudaddecerca-api.proconsi.com/Categoria?idCategoriaPadre=30&idIdioma=0&idProyecto=1"
    suspend fun getLugares(): List<Lugar> {
        return try {
            println("Llamando a la API: $apiUrl")
            val respuesta: ApiResponse = KtorClient.httpClient.get(apiUrl).body()
            respuesta.fichas
        } catch (e: Exception) {
            println("Error al obtener los lugares: ${e.message}")
            emptyList()
        }
    }
}

package org.proconsi.multiplatform.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.proconsi.multiplatform.data.createHttpClient
import org.proconsi.multiplatform.data.remote.dto.ApiResponse
import org.proconsi.multiplatform.domain.model.toElemento
import org.proconsi.multiplatform.domain.model.Elemento

class LugarRepo {
    private val httpClient = createHttpClient()
    private val apiUrl = "https://tuciudaddecerca-api.proconsi.com/Categoria"

    suspend fun getLugares(): List<Elemento> {
        return try {
            val apiResponse = httpClient.get(apiUrl) {
                parameter("idCategoriaPadre", 30)
                parameter("idIdioma", 0)
                parameter("idProyecto", 1)
            }.body<ApiResponse>()

            val elementos = apiResponse.fichas.map {
                it.toElemento()
            }

            println("LugarRepo: ÉXITO. Se mapearon ${elementos.size} elementos.")
            return elementos

        } catch (e: Exception) {
            println("Error en LugarRepo: ${e.message}")
            e.printStackTrace()
            emptyList()
        }
    }
}

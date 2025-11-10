package org.proconsi.multiplatform.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.proconsi.multiplatform.data.createHttpClient
import org.proconsi.multiplatform.data.remote.dto.ApiResponse
import org.proconsi.multiplatform.domain.model.toElemento
import org.proconsi.multiplatform.domain.model.Elemento
import org.proconsi.multiplatform.domain.repository.LugarRepository

class LugarApi : LugarRepository {
    private val httpClient = createHttpClient()
    private val apiUrl = "https://tuciudaddecerca-api.proconsi.com/Categoria"

    override suspend fun getLugares(): List<Elemento> {
        return try {
            /* Hace protocolo HTTP get para obtener datos y añade
               los parametros de la URL para filtrar la peticion.
               Una vez se recibe la respuesta del servidor se convierte
               en un objeto de clas ApiResponse */
            val apiResponse = httpClient.get(apiUrl) {
                parameter("idCategoriaPadre", 30)
                parameter("idIdioma", 0)
                parameter("idProyecto", 1)
            }.body<ApiResponse>()

            /* Recibe la lista de lugares (apiResponse.fichas) y se transforma
               a otra lista, cada elemento de la lista (it) se transforma de Lugar
               a Elemento (.toElemento)
             */
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

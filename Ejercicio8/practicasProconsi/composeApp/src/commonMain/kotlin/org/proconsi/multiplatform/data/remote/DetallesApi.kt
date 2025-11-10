package org.proconsi.multiplatform.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import org.proconsi.multiplatform.data.remote.dto.Lugar
import org.proconsi.multiplatform.data.createHttpClient

class DetallesApi {
    private val httpClient = createHttpClient()
    private val baseUrl = "https://tuciudaddecerca-api.proconsi.com/Ficha"
    suspend fun getDetalles(idFicha: Int): Lugar? {
        val apiUrl = "$baseUrl?idFicha=$idFicha&TipoFicha=F&idIdioma=0&idProyecto=1"
        return try {
            println("Llamando a la API de detalles: $apiUrl")
            httpClient.get(apiUrl).body<Lugar>()
        } catch (e: Exception) {
            println("Error al obtener los detalles para el ID $idFicha: ${e.message}")
            e.printStackTrace()
            null
        }
    }
}

package org.proconsi.multiplatform.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lugar(
    @SerialName("idFicha")
    val id: Int,
    val latitud: Double,
    val longitud: Double,

    val nombre: String,

    @SerialName("descripcionCorta")
    val descripcion: String? = null,

    @SerialName("urlImagen")
    val imageUrl: String? = null,

    val fechaInicio: String? = null,
    val horaInicio: String? = null,
    val fechaFin: String? = null,
    val horaFin: String? = null,

    @SerialName("distanciaUsuarioMetros")
    val distanciaUsuarioMetros: Double? = null,

    val tipoFicha: String? = null,
    val orden: Int? = null,

    var esFavorito: Boolean = false
)

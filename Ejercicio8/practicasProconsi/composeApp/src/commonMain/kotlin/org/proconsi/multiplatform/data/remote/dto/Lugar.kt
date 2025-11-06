package org.proconsi.multiplatform.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Lugar(
    @SerialName("idFicha")
    val id: Int,
    val nombre: String,
    val descripcionCorta: String? = null,
    val fechaInicio: String? = null,
    val horaInicio: String? = null,
    val fechaFin: String? = null,
    val horaFin: String? = null,
    val latitud: Double,
    val longitud: Double,
    val urlImagen: String? = null,
    val distanciaUsuarioMetros: Double? = null,
    val tipoFicha: String? = null,
    val orden: Int? = null,

    @Transient
    var esFavorito: Boolean = false
)
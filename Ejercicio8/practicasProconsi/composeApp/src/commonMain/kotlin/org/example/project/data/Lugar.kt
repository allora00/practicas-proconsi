package org.example.project.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lugar(
    val idFicha: Int,
    val nombre: String,
    val descripcionCorta: String,
    val fechaInicio: String? = null,
    val horaInicio: String? = null,
    val fechaFin: String? = null,
    val horaFin: String? = null,
    val latitud: Double,
    val longitud: Double,
    val urlImagen: String,
    val distanciaUsiarioMetros: Double? = null,
    val tipoFicha: String,
    val orden: Int? = null
)
    
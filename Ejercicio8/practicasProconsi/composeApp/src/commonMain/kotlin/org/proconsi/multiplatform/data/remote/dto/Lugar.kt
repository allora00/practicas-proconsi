package org.proconsi.multiplatform.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lugar(
    @SerialName("idFicha")
    val id: Int,
    val nombre: String,
    val urlImagen: String,

    val descripcion: String? = null,
    val descripcionCorta: String? = null,
    val direccion: String? = null,
    val email: String? = null,
    val telefono: Int? = null,
    val tipoFicha: String? = null,
    val orden: Int? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    val fechaInicio: String? = null,
    val horaInicio: String? = null,
    val fechaFin: String? = null,
    val horaFin: String? = null,
    val importancia: Int? = null,
    val esFavorito: Boolean? = null,

    val media: Media? = null,
    val rutas: List<Ruta> = emptyList(),
    val subFichas: List<SubFicha> = emptyList()
)

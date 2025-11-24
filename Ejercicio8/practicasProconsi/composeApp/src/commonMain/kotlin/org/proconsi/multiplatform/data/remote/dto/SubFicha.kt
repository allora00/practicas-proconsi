package org.proconsi.multiplatform.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubFicha(
    @SerialName("idDetalle")
    val idDetalle: Int,

    @SerialName("idFicha")
    val id: Int,
    val nombre: String,
    val descripcionCorta: String?,
    val descripcion: String?,
    val fechaInicio: String?,
    val horaInicio: String?,
    val fechaFin: String?,
    val horaFin: String?,
    val idIdioma: Int?,
    val idImagen: Int?,
    val latitud: Double?,
    val longitud: Double?,
    val direccion: String?,
    val email: String?,
    val telefono: String?,
    val importancia: Int?,
    val urlImagen: String?,
    val media: Media? = null,
    val rutas: List<Ruta> = emptyList(),
    val promociones: List<String> = emptyList(),
    val subFichas: List<SubFicha> = emptyList()
)
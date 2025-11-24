package org.proconsi.multiplatform.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ruta(
    @SerialName("idRuta")
    val id: Int,
    val nombre: String,
    val descripcion: String? = null
)

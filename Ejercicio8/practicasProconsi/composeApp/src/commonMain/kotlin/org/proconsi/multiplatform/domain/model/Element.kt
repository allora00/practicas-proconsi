package org.proconsi.multiplatform.domain.model

data class Elemento(
    val id: Int,
    val titulo: String,
    val descripcionCorta: String,
    val urlImagen: String,
    var esFavorito: Boolean = false
)

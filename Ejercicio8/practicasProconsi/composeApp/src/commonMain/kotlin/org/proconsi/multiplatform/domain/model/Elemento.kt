package org.proconsi.multiplatform.domain.model

//Modelo de dominio, uso los datos necesarios del DTO de Lugar
data class Elemento(
    val id: Int,
    val nombre: String,
    val descripcionCorta: String,
    val urlImagen: String,
    var esFavorito: Boolean = false
)

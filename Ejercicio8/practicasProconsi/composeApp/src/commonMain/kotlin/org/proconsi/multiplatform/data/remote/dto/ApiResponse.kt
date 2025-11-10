package org.proconsi.multiplatform.data.remote.dto

import kotlinx.serialization.Serializable

//Clase serializable que recibe una lista de Lugares llamada ficha
@Serializable
data class ApiResponse(
    val fichas: List<Lugar>
)
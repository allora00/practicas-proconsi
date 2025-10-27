package org.proconsi.multiplatform.data

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val fichas: List<Lugar>
)

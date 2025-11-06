package org.proconsi.multiplatform.data.remote.dto

import kotlinx.serialization.Serializable
import org.proconsi.multiplatform.data.remote.dto.Lugar

@Serializable
data class ApiResponse(
    val fichas: List<Lugar>
)
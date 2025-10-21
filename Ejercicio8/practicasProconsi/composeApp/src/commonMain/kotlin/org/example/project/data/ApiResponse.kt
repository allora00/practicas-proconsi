package org.example.project.data

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val fichas: List<Lugar>
)

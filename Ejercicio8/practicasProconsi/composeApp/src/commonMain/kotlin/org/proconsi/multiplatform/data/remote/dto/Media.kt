package org.proconsi.multiplatform.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val images: List<String> = emptyList(),
    val audios: List<String> = emptyList(),
    val videos: List<String> = emptyList(),
    val links: List<String> = emptyList()
)

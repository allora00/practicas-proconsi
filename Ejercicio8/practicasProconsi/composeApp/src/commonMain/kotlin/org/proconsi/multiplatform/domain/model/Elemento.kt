package org.proconsi.multiplatform.domain.model

data class Elemento(
    val id: Int,
    val nombre: String,
    val descripcion: String?,
    val descripcionCorta: String?,
    val direccion: String?,
    val urlImagen: String,
    val tipoFicha: String?,
    val orden: Int?,
    val telefono: Int?,
    val email: String?,
    var esFavorito: Boolean,


    val media: Media?,
    val rutas: List<Ruta>,
    val subFichas: List<SubFicha>

)

data class Media(
    val images: List<String>,
    val audios: List<String>,
    val videos: List<String>
)

data class Ruta(
    val id: Int,
    val nombre: String,
    val descripcion: String?
)

data class SubFicha(
    val id: Int,
    val nombre: String,
    val descripcionCorta: String?,
    val urlImagen: String?
)

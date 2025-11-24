package org.proconsi.multiplatform.domain.model

import org.proconsi.multiplatform.data.remote.dto.Lugar as LugarDto
import org.proconsi.multiplatform.data.remote.dto.Media as MediaDto
import org.proconsi.multiplatform.data.remote.dto.Ruta as RutaDto
import org.proconsi.multiplatform.data.remote.dto.SubFicha as SubFichaDto

fun LugarDto.toElemento(): Elemento {
    return Elemento(
        id = this.id,
        nombre = this.nombre,
        descripcion = this.descripcion,
        descripcionCorta = this.descripcionCorta,
        direccion = this.direccion,
        urlImagen = this.urlImagen,
        tipoFicha = this.tipoFicha,
        orden = this.orden,
        email = this.email,
        telefono = this.telefono,
        esFavorito = this.esFavorito ?: false,
        media = this.media?.toDomain(),
        rutas = this.rutas.map { it.toDomain() },
        subFichas = this.subFichas.map { it.toDomain() }
    )
}

fun MediaDto.toDomain(): Media {
    return Media(
        images = this.images,
        audios = this.audios,
        videos = this.videos
    )
}

fun RutaDto.toDomain(): Ruta {
    return Ruta(
        id = this.id,
        nombre = this.nombre,
        descripcion = this.descripcion
    )
}

fun SubFichaDto.toDomain(): SubFicha {
    return SubFicha(
        id = this.id,
        nombre = this.nombre,
        descripcionCorta = this.descripcionCorta,
        urlImagen = this.urlImagen
    )
}

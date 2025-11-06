package org.proconsi.multiplatform.domain.model

import org.proconsi.multiplatform.data.remote.dto.Lugar

fun Lugar.toElemento(): Elemento {
    return Elemento(
        id = this.id,
        titulo = this.nombre,
        descripcionCorta = this.descripcionCorta,
        urlImagen = this.urlImagen,
        esFavorito = false
    )
}

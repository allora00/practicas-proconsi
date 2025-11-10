package org.proconsi.multiplatform.domain.model

import org.proconsi.multiplatform.data.remote.dto.Lugar

//Convierte un elemento de tipo Lugar a tipo Elemento
fun Lugar.toElemento(): Elemento {
    return Elemento(
        id = this.id,
        nombre = this.nombre,
        descripcionCorta = this.descripcionCorta,
        urlImagen = this.urlImagen,
        esFavorito = false
    )
}

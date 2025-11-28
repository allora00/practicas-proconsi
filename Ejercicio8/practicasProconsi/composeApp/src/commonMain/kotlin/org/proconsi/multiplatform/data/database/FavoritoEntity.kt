package org.proconsi.multiplatform.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity convierte la clase en uan tabla para la base de datos
@Entity(tableName = "favoritos")
data class FavoritoEntity(
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val urlImagen: String,
    val descripcionCorta: String?
)

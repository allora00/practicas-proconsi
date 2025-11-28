package org.proconsi.multiplatform.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

// Le especificamos a Room las entidades que se van a guardar
@Database(
    // Lista de las tablas que se van a gestionar
    entities = [FavoritoEntity::class],
    // Numero para controlar migraciones
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
     abstract fun favoritoDao(): FavoritoDAO
}

expect fun getAppDatabase(): AppDatabase

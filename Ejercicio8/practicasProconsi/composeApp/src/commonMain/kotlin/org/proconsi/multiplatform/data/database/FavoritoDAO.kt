package org.proconsi.multiplatform.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(favorito: FavoritoEntity)

    @Query("DELETE FROM favoritos WHERE id = :id")
    suspend fun borrar(id: Int)

    @Query("SELECT * FROM favoritos ORDER BY nombre ASC")
    fun obtenerTodos(): Flow<List<FavoritoEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favoritos WHERE id = :id LIMIT 1)")
    fun esFavorito(id: Int): Flow<Boolean>
}

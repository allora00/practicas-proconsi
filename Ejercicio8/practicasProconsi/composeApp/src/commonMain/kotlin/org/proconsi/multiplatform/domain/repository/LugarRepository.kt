package org.proconsi.multiplatform.domain.repository

import org.proconsi.multiplatform.domain.model.Elemento

interface LugarRepository {
    suspend fun getLugares(): List<Elemento>
}

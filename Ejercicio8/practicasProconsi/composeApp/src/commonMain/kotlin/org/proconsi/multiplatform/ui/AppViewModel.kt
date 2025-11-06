package org.proconsi.multiplatform.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.proconsi.multiplatform.data.remote.dto.Lugar
import org.proconsi.multiplatform.data.remote.LugarRepo

data class LugaresUiState(
    val lugares: List<Lugar> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class AppViewModel(private val lugarRepo: LugarRepo) : ViewModel() {

    private val _uiState = MutableStateFlow(LugaresUiState())
    val uiState = _uiState.asStateFlow()

    init {
        println("AppViewModel INICIADO. Cargando la lista de lugares...")
        cargarListaDeLugares()
    }

    fun cargarListaDeLugares() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val listaDeLugares = lugarRepo.getLugares()
                println("ÉXITO: Se recibieron ${listaDeLugares.size} lugares.")
                _uiState.update {
                    it.copy(
                        lugares = listaDeLugares,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                println("ERROR en la llamada de red: ${e.message}")
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Error al cargar los datos: ${e.message}"
                    )
                }
            }
        }
    }
}

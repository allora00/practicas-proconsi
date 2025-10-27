package org.proconsi.multiplatform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.proconsi.multiplatform.data.Lugar
import org.proconsi.multiplatform.data.LugarRepo
import org.proconsi.multiplatform.data.DetallesRepo

enum class Screen { LISTA, DETALLE, FAVORITOS }

data class AppUiState(
    val lugares: List<Lugar> = emptyList(),
    val estaCargando: Boolean = false,
    val pantallaActual: Screen = Screen.LISTA,
)

class AppViewModel : ViewModel() {
    private val repository = LugarRepo()
    private val detallesRepository = DetallesRepo()

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    init {
       cargarLugares()
    }

    private fun cargarLugares() {
        viewModelScope.launch {
            _uiState.update { it.copy(estaCargando = true) }
            val lugares = repository.getLugares()
            _uiState.update { it.copy(lugares = lugares, estaCargando = false) }
        }
    }

}

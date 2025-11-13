package org.proconsi.multiplatform.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.proconsi.multiplatform.data.remote.LugarApi
import org.proconsi.multiplatform.domain.model.Elemento

//Data class para guardar la lista de lugares
data class LugaresUiState(
    val lugares: List<Elemento> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

//Data class para guardar los detalles de los lugares
data class DetallesUiState(
    val lugar: Elemento? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

//Actua de puente entre la logica y la interfaz
class AppViewModel(private val lugarApi: LugarApi) : ViewModel() {

    //Crea contenedor de estado de la ui
    private val _lugaresUiState = MutableStateFlow(LugaresUiState())
    val lugaresUiState = _lugaresUiState.asStateFlow()

    private val _detallesUiState = MutableStateFlow(DetallesUiState())
    val detallesUiState = _detallesUiState.asStateFlow()

    init {
        println("Cargando la lista de lugares...")
        cargarListaDeLugares()
    }

    fun cargarListaDeLugares() {
        //Pone el estado a cargando
        _lugaresUiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                //Se cogen TODOS los lugares de la lista de LugarApi a la vez
                val listaDeLugares: List<Elemento> = lugarApi.getLugares()
                println("Se recibieron ${listaDeLugares.size} lugares.")
                //Si es exitoso, se actualizan los estados con los datos
                _lugaresUiState.update {
                    it.copy(
                        lugares = listaDeLugares,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                println("ERROR en la llamada de red: ${e.message}")
                _lugaresUiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Error al cargar los datos: ${e.message}"
                    )
                }
            }
        }
    }

    fun cargarDetallesDeLugar(id: Int) {
        _detallesUiState.update { it.copy(isLoading = true) }
        val lugarEncontrado = _lugaresUiState.value.lugares.find { it.id == id }

        if (lugarEncontrado != null) {
           _detallesUiState.update {
                it.copy(
                    lugar = lugarEncontrado,
                    isLoading = false,
                    error = null
                )
            }
        } else {
            _detallesUiState.update {
                it.copy(
                    isLoading = false,
                    error = "No se pudo encontrar el lugar con ID $id"
                )
            }
        }
    }
}

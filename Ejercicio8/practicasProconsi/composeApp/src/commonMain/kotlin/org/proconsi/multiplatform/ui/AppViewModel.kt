package org.proconsi.multiplatform.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.proconsi.multiplatform.data.remote.LugarApi
import org.proconsi.multiplatform.domain.model.Elemento

//Data class, solo para guardar datos
data class LugaresUiState(
    val lugares: List<Elemento> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

//Actua de puente entre la logica y la interfaz
class AppViewModel(private val lugarApi: LugarApi) : ViewModel() {

    //Crea contenedor de estado de la ui
    private val _uiState = MutableStateFlow(LugaresUiState())
    val uiState = _uiState.asStateFlow()

    init {
        println("Cargando la lista de lugares...")
        cargarListaDeLugares()
    }

    fun cargarListaDeLugares() {
        //Pone el estado a cargando
        _uiState.update { it.copy(isLoading = true) }
        //Inicia corrutina
        viewModelScope.launch {
            try {
                //Se cogen los lugares de la lista de LugarApi
                val listaDeLugares: List<Elemento> = lugarApi.getLugares()
                println("Se recibieron ${listaDeLugares.size} lugares.")
                //Si es exitoso, se actualizan los estados con los datos
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

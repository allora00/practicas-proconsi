package org.proconsi.multiplatform.ui.detalles

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import io.ktor.client.HttpClient
import org.proconsi.multiplatform.data.remote.DetallesApi
import org.proconsi.multiplatform.data.remote.LugarApi
import org.proconsi.multiplatform.ui.AppViewModel

data class DetallesScreen(val lugarId: Int) : Screen {

    @Composable
    override fun Content() {
        val appViewModel: AppViewModel = rememberScreenModel {
            val httpClient = HttpClient()
            val lugarApi = LugarApi(httpClient)
            val detallesApi = DetallesApi(httpClient)
            AppViewModel(lugarApi, detallesApi)
        }
        val detallesState by appViewModel.detallesUiState.collectAsState()

        LaunchedEffect(lugarId) {
            appViewModel.cargarDetallesDeLugar(lugarId)
        }

        when {
            detallesState.isLoading -> {
                CircularProgressIndicator()
            }
            detallesState.error != null -> {
                Text("Error: ${detallesState.error}")
            }
            detallesState.lugar != null -> {
                Text("Detalles de ${detallesState.lugar!!.nombre} cargados")
            }
        }
    }
}

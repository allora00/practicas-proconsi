package org.proconsi.multiplatform.ui.lista

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.proconsi.multiplatform.data.remote.LugarApi
import org.proconsi.multiplatform.domain.model.Elemento
import org.proconsi.multiplatform.ui.AppViewModel
import org.proconsi.multiplatform.ui.detalles.DetallesScreen
import cafe.adriel.voyager.core.model.rememberScreenModel
import org.proconsi.multiplatform.data.createHttpClient
import org.proconsi.multiplatform.data.remote.DetallesApi

object ListScreen : Screen {

    @Composable
    override fun Content() {
        val appViewModel: AppViewModel = rememberScreenModel {
            val httpClient = createHttpClient()
            val lugarApi = LugarApi(httpClient)
            val detallesApi = DetallesApi(httpClient)
            AppViewModel(lugarApi, detallesApi)
        }
        val uiState by appViewModel.lugaresUiState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator()
                }
                uiState.error != null -> {
                    Text(text = "Error: ${uiState.error}")
                }
                uiState.lugares.isNotEmpty() -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(uiState.lugares) { lugar ->
                            LugarItem(
                                lugar = lugar,
                                modifier = Modifier.clickable {
                                    navigator.push(DetallesScreen(lugarId = lugar.id))
                                }
                            )
                            HorizontalDivider()
                        }
                    }
                }
                else -> {
                    Text(text = "No se encontraron lugares.")
                }
            }
        }
    }
}

//Metodo para imprimir la imagen del lugar
@Composable
fun LugarItem(lugar: Elemento, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        KamelImage(
            resource = asyncPainterResource(data = lugar.urlImagen),
            contentDescription = "Imagen de ${lugar.nombre}",
            modifier = Modifier.size(80.dp),

            onLoading = { progress -> CircularProgressIndicator(progress) },
            onFailure = {
                println("Error al cargar la imagen: ${it.message}")
                Text("Error")}
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = lugar.nombre)
    }
}
package org.proconsi.multiplatform.ui.detalles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.proconsi.multiplatform.domain.model.Elemento
import org.proconsi.multiplatform.ui.AppViewModel
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.proconsi.multiplatform.data.createHttpClient
import org.proconsi.multiplatform.data.remote.DetallesApi
import org.proconsi.multiplatform.data.remote.LugarApi


data class DetallesScreen(val lugarId: Int) : Screen {
    // El OptIn es necesario para decirle al compilador que elimine la advertencia
    // y decirle que quieres usar una API "experimental" para que pueda usarla
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val httpClient = createHttpClient()
        val lugarApi = LugarApi(httpClient)
        val detallesApi = DetallesApi(httpClient)
        val appViewModel: AppViewModel = rememberScreenModel<AppViewModel>{
            AppViewModel(lugarApi, detallesApi)
        }
        val detallesState by appViewModel.detallesUiState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(lugarId) {
            appViewModel.cargarDetallesDeLugar(lugarId)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        val nombre = detallesState.lugar?.nombre ?: "Detalles"
                        Text(nombre)
                    },
                    navigationIcon = {
                       if (navigator.canPop) {
                            IconButton(onClick = { navigator.pop() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Volver a la lista"
                                )
                            }
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                when {
                    detallesState.isLoading -> {
                        CircularProgressIndicator()
                    }
                    detallesState.error != null -> {
                        Text("Error: ${detallesState.error}")
                    }
                    detallesState.lugar != null -> {
                        decorarDetalles(lugar = detallesState.lugar!!)
                    }
                }
            }
        }
    }

    @Composable
    private fun decorarDetalles(lugar: Elemento) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Fragmento encargado de mostrar la imagen del lugar
            KamelImage(
                resource = asyncPainterResource(data = lugar.urlImagen),
                contentDescription = "Imagen de ${lugar.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop,
                onLoading = { CircularProgressIndicator(it) },
                onFailure = { Text("¡Ups! No se pudo cargar la imagen") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Columna con la informacion (descripcion, email, direccion, etc)
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = lugar.nombre,
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = lugar.descripcionCorta?.corrigeTexto()
                        ?: AnnotatedString("Sin descripción corta."),
                    style = MaterialTheme.typography.titleMedium
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                Text(
                    text = lugar.descripcion?.corrigeTexto()
                        ?: AnnotatedString("No hay descripción disponible."),
                    style = MaterialTheme.typography.bodyLarge
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                Text(
                    text = lugar.direccion?.corrigeTexto() ?: AnnotatedString("No hay dirección disponible."),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = lugar.email?.corrigeTexto() ?: AnnotatedString("No hay email disponible."),
                    style = MaterialTheme.typography.titleMedium,
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            }
        }
    }

    // Metodo que elimina los caracteres erroneos de los textos de los lugares
    fun String.corrigeTexto(): AnnotatedString {
        val parts = this.split(Regex("(?i)<br\\s*/?>"))
        return buildAnnotatedString {
            parts.forEachIndexed { index, part ->
                append(part)
                if (index < parts.size - 1) {
                    append("\n")
                }
            }
        }
    }
}
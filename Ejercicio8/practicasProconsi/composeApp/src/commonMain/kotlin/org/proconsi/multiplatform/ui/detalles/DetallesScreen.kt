package org.proconsi.multiplatform.ui.detalles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

import org.proconsi.multiplatform.data.createHttpClient
import org.proconsi.multiplatform.data.database.getAppDatabase
import org.proconsi.multiplatform.data.remote.DetallesApi
import org.proconsi.multiplatform.data.remote.LugarApi
import org.proconsi.multiplatform.domain.model.Elemento
import org.proconsi.multiplatform.ui.AppViewModel

data class DetallesScreen(val lugarId: Int) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val appViewModel: AppViewModel = rememberScreenModel {
            val httpClient = createHttpClient()
            val database = getAppDatabase()
            val favoritoDao = database.favoritoDao()
            val lugarApi = LugarApi(httpClient)
            val detallesApi = DetallesApi(httpClient)
            AppViewModel(lugarApi, detallesApi, favoritoDao)
        }

        val detallesState by appViewModel.detallesUiState.collectAsState()
        val esFavorito by appViewModel.esLugarFavorito(lugarId).collectAsState()

        LaunchedEffect(lugarId) {
            appViewModel.cargarDetallesDeLugar(lugarId)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(detallesState.lugar?.nombre ?: "Detalles") },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver"
                            )
                        }
                    },
                    actions = {
                        if (detallesState.lugar != null) {
                            IconButton(onClick = {
                                appViewModel.toggleFavorito(detallesState.lugar!!)
                            }) {
                                Icon(
                                    imageVector = if (esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Marcar como favorito",
                                    tint = if (esFavorito) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                when {
                    detallesState.isLoading -> CircularProgressIndicator()
                    detallesState.error != null -> Text("Error: ${detallesState.error}")
                    detallesState.lugar != null -> decorarDetalles(lugar = detallesState.lugar!!)
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
            KamelImage(
                resource = asyncPainterResource(data = lugar.urlImagen),
                contentDescription = "Imagen de ${lugar.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop,
                onLoading = { CircularProgressIndicator(it) },
                onFailure = { Text("No se pudo cargar la imagen") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(lugar.nombre, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = lugar.descripcionCorta?.corrigeTexto() ?: AnnotatedString("Sin descripción corta."),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }

    fun String.corrigeTexto(): AnnotatedString {
        val parts = this.split(Regex("(?i)<br\\s*/?>"))
        return buildAnnotatedString {
            parts.forEachIndexed { index, part ->
                append(part)
                if (index < parts.lastIndex) {
                    append("\n")
                }
            }
        }
    }
}

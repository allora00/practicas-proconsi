package org.proconsi.multiplatform.ui.lista

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.proconsi.multiplatform.ui.LugaresUiState
import coil.compose.AsyncImage

@Composable
fun ListScreen(uiState: LugaresUiState) {

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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp), // Añade espacio alrededor de cada fila
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            coil.compose.AsyncImage(
                                model = lugar.urlImagen,
                                contentDescription = "Imagen de ${lugar.nombre}",
                                modifier = Modifier.size(64.dp)
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Text(
                                text = lugar.nombre,
                                fontWeight = FontWeight.Bold
                            )
                        }

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
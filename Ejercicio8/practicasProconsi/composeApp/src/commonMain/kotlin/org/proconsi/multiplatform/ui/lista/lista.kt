package org.proconsi.multiplatform.ui.lista

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.proconsi.multiplatform.ui.LugaresUiState
import io.kamel.image.KamelImage

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
                        Text(text = lugar.nombre, modifier = Modifier.padding(16.dp))
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



/////PONER VIEWMODEL EN EL LISTADO
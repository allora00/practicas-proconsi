package org.proconsi.multiplatform

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import org.proconsi.multiplatform.ui.lista.ListScreen // Asegúrate de que esta importación es correcta

@Composable
internal fun App(appViewModel: AppViewModel = viewModel { AppViewModel() }) {
    val uiState by appViewModel.uiState.collectAsState()

    MaterialTheme {
        ListScreen(uiState)
    }
}
    
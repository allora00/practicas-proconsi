package org.proconsi.multiplatform

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import org.proconsi.multiplatform.data.remote.LugarApi
import org.proconsi.multiplatform.ui.AppViewModel
import org.proconsi.multiplatform.ui.lista.ListScreen

@Composable
internal fun App() {
    val lugarApi = LugarApi()
    val appViewModel: AppViewModel = viewModel { AppViewModel(lugarApi) }
    val uiState by appViewModel.uiState.collectAsState()

    MaterialTheme {
        ListScreen(uiState = uiState)
    }
}

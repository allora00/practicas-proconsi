package org.proconsi.multiplatform

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import org.proconsi.multiplatform.data.remote.LugarApi
import org.proconsi.multiplatform.ui.AppViewModel
import org.proconsi.multiplatform.ui.lista.ListScreen
import org.proconsi.multiplatform.ui.NavRoutes
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import java.awt.Button

@Composable
internal fun App() {
    val lugarApi = LugarApi()
    val appViewModel: AppViewModel = viewModel { AppViewModel(lugarApi) }
    val lugaresState by appViewModel.lugaresUiState.collectAsState()
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.LISTA
        ) {
            composable(NavRoutes.LISTA) {
                ListScreen(
                    Button(
                        uiState = lugaresState,
                        onClick = { lugar ->
                            navController.navigate("${NavRoutes.DETALLES}/${lugar.id}")
                        }
                    )
                )
            }
        }
    }
}

package org.proconsi.multiplatform.ui.favoritos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.core.model.rememberScreenModel
import org.proconsi.multiplatform.ui.AppViewModel
import androidx.compose.runtime.getValue
import org.proconsi.multiplatform.data.createHttpClient
import org.proconsi.multiplatform.data.remote.DetallesApi
import org.proconsi.multiplatform.data.remote.LugarApi


//Esta clase es de tipo 'object' porque no recibe ningun parametro por lo
// que asi es mas simple
object FavoritosScreen : Screen {
    //El OptIn es necesario para decirle al compilador que elimine la advertencia
    // decirle que queires usar una API "experimental" para que pueda usarla
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val httpClient = createHttpClient()
        val lugarApi = LugarApi(httpClient)
        val detallesApi = DetallesApi(httpClient)
        val navigator = LocalNavigator.currentOrThrow
        val appViewModel: AppViewModel = rememberScreenModel<AppViewModel>{
            AppViewModel(lugarApi, detallesApi)
        }


        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Lugares Favoritos") },
                    navigationIcon = {
                        // Botón para volver a la pantalla anterior
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Pantalla de favoritos")
            }
        }
    }
}

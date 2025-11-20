package org.proconsi.multiplatform

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.proconsi.multiplatform.ui.lista.ListScreen

@Composable
internal fun App() {

    MaterialTheme {
        Navigator(screen = ListScreen){ navigator ->
            SlideTransition(navigator)
        }
    }
}

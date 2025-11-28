package org.proconsi.multiplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        org.proconsi.multiplatform.data.database.initAndroidDatabase(this)
        setContent { App() }
    }
}

    
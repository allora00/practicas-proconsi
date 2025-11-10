package org.proconsi.multiplatform.data

import io.ktor.client.HttpClient

//Clase que llama a la funcion de crear cliente en androidMain
expect fun createHttpClient(): HttpClient
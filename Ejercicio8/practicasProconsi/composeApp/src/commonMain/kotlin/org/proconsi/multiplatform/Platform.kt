package org.proconsi.multiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package org.proconsi.multiplatform.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver


private lateinit var appContext: Context

fun initAndroidDatabase(context: Context) {
    appContext = context.applicationContext
}

actual fun getAppDatabase(): AppDatabase {
    if (!::appContext.isInitialized) {
        throw IllegalStateException("El contexto de Android no se ha inicializado. Llama a initAndroidDatabase() en tu MainActivity.")
    }

    val dbFile = appContext.getDatabasePath("app_database.db")
    return Room.databaseBuilder<RoomDatabase>(
        context = appContext,
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .build() as AppDatabase
}

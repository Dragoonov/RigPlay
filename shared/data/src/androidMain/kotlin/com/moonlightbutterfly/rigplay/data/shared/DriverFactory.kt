package com.moonlightbutterfly.rigplay.data.shared

import android.content.Context
import com.moonlightbutterfly.rigplay.RigPlayDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(RigPlayDatabase.Schema, context, "RigPlayDatabase.db")
    }
}
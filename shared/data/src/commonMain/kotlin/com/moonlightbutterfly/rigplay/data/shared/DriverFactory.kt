package com.moonlightbutterfly.rigplay.data.shared

import com.moonlightbutterfly.rigplay.RigPlayDatabase
import com.squareup.sqldelight.db.SqlDriver

fun createDatabase(driverFactory: DriverFactory): RigPlayDatabase {
    val driver = driverFactory.createDriver()
    return RigPlayDatabase(driver)
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}
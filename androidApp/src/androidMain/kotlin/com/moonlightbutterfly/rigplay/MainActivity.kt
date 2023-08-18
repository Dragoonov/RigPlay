package com.moonlightbutterfly.rigplay

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.defaultComponentContext
import com.moonlightbutterfly.rigplay.root.RootContent
import com.moonlightbutterfly.rigplay.root.RootFactory
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val rootFactory: RootFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootContent(rootFactory.create(defaultComponentContext()))
        }
    }
}
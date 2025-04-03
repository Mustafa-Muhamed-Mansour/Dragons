package com.dragons.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.dragons.app.di.initKoin

fun main(){
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Dragons",
        ) {
            App()
        }
    }
}
package com.github.fajaragungpramana.pokelib.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.fajaragungpramana.pokelib.ui.theme.PokeLibTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokeLibTheme(dynamicColor = false) {
                MainView.ContentView()
            }
        }

    }

}
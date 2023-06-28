package com.github.fajaragungpramana.pokelib.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.fajaragungpramana.pokelib.ui.theme.PokeLibTheme

object MainView {

    @Composable
    fun ContentView() {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize()
        ) {

        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainActivity_Preview() {
    PokeLibTheme(dynamicColor = false) {
        MainView.ContentView()
    }
}
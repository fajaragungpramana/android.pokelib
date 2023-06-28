package com.github.fajaragungpramana.pokelib.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.fajaragungpramana.pokelib.R
import com.github.fajaragungpramana.pokelib.ui.theme.PokeLibTheme
import com.github.fajaragungpramana.pokelib.widget.component.TextFieldRoundedWithStartIcon

object MainView {

    @Composable
    fun ContentView() {

        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextFieldRoundedWithStartIcon(
                    modifier = Modifier.weight(1f),
                    iconId = R.drawable.ic_search,
                    placeHolderText = stringResource(id = R.string.search_pokemon_you_here)
                )

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 16.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_favorite),
                        contentDescription = null
                    )
                }

            }

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
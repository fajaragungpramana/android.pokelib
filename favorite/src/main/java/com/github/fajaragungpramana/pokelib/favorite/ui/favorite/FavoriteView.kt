package com.github.fajaragungpramana.pokelib.favorite.ui.favorite

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.github.fajaragungpramana.pokelib.R
import com.github.fajaragungpramana.pokelib.widget.style.Black80

object FavoriteView {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContentView(factory: ViewModelProvider.Factory) {
        val viewModel: FavoriteViewModel = viewModel(factory = factory)

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Black80,
                            text = stringResource(R.string.my_favorites),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        val activity = (LocalContext.current as Activity)
                        IconButton(onClick = {
                            activity.finish()
                        }) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {

                            AsyncImage(
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp),
                                model = R.drawable.ic_sort,
                                contentDescription = null
                            )

                        }
                    }
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                }
            }
        )

    }

}
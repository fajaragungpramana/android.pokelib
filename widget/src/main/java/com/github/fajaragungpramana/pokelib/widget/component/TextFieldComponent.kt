package com.github.fajaragungpramana.pokelib.widget.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.github.fajaragungpramana.pokelib.widget.style.Gray20
import com.github.fajaragungpramana.pokelib.widget.style.Gray40

@Composable
fun TextFieldRoundedWithStartIcon(
    modifier: Modifier,
    @DrawableRes iconId: Int,
    placeHolderText: String
) {
    val searchValue = remember { mutableStateOf(TextFieldValue()) }

    BasicTextField(
        modifier = modifier,
        value = searchValue.value,
        onValueChange = { searchValue.value = it },
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Gray20, shape = RoundedCornerShape(20.dp))
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier.padding(end = 12.dp),
                    painter = painterResource(id = iconId),
                    contentDescription = null
                )

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (searchValue.value.text.isEmpty())
                        Text(
                            text = placeHolderText,
                            color = Gray40
                        )

                    innerTextField()
                }

            }
        }
    )
}
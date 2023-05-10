package com.jdm.app.movieappcompose.ui.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jdm.app.movieappcompose.R

@Composable
fun Favorite(
) {
    Column() {
        Text(text = stringResource(id = R.string.str_favorite))
    }

}
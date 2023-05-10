package com.jdm.app.movieappcompose.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.jdm.app.movieappcompose.R
import com.jdm.app.movieappcompose.component.BasicTextField
import com.jdm.app.movieappcompose.component.getTextState
import com.jdm.app.movieappcompose.ui.theme.C_252525
import com.jdm.app.movieappcompose.ui.theme.C_acb5bd
import com.jdm.domain.model.Movie


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel
) {
    val uiState by viewModel.viewState.collectAsState()
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 16.dp, end = 16.dp)) {
        Box(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp)
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart),
                shape = RoundedCornerShape(8.dp),
                value = uiState.keyword,
                onValueChange = {
                    viewModel.setKeyword(it)
                },
                placeholder = { Text(text = stringResource(id = R.string.str_search)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = androidx.compose.material3.TextFieldDefaults.textFieldColors(
                    containerColor = C_acb5bd,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = C_252525
                )

            )
            IconButton(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterEnd),
                onClick = {
                    viewModel.setEvent(SearchContract.SearchIntent.ClickSearchEvent)
                }) {
                Icon(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_24_000000),
                    contentDescription = null
                )
            }
        }
        /* list */
        SearchResultScreen(
            uiState.movieList
        )


    }
    /* Search */

}

@Composable
fun SearchResultScreen(
    movieList: MutableList<Movie>
) {
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return super.onPreScroll(available, source)
            }
        }
    }
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(
            movieList.toList()
        ) { index, item ->
            SearchPoster(movie = item)
        }
    }
}

@Composable
private fun SearchPoster(
    modifier: Modifier = Modifier,
    movie: Movie,
    selectMovie: (Movie) -> Unit = {}
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clickable(
                onClick = { selectMovie(movie) }
            ),
        color = Color.White,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout {
            val (image, title, year, type, favorite) = createRefs()
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(0.8f)
                    .padding(top = 8.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                model = movie.poster,
                contentDescription = null
            )
            IconButton(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .constrainAs(favorite) {
                        end.linkTo(parent.end)
                        top.linkTo(title.top)
                        bottom.linkTo(type.bottom)
                    },
                onClick = {}) {
                Icon(
                    modifier = Modifier.width(24.dp).height(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_heart_stroke_16_acb5bd),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(title) {
                        top.linkTo(image.bottom, margin = 16.dp)
                        start.linkTo(parent.start, margin = 8.dp)
                    },
                text = movie.title,
                maxLines = 1,
                fontSize = 12.sp,
                color = C_252525
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(year) {
                        top.linkTo(title.bottom, margin = 4.dp)
                        start.linkTo(parent.start, margin = 8.dp)
                    },
                text = movie.year,
                maxLines = 1,
                fontSize = 12.sp,
                color = C_252525
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(type) {
                        top.linkTo(year.bottom, margin = 4.dp)
                        start.linkTo(parent.start, margin = 8.dp)
                    },
                text = movie.type,
                maxLines = 1,
                fontSize = 12.sp,
                color = C_252525
            )


        }

    }
}

package com.jdm.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.jdm.design.R
import com.jdm.design.theme.HToggleButton
import com.jdm.design.theme.JdmTheme
import com.jdm.design.theme.SearchTextField
import com.jdm.model.Movie
import com.jdm.search.SearchContract.SearchEvent
import com.jdm.search.SearchContract.SearchEvent.MoreSearch
import com.jdm.search.SearchContract.SearchEvent.OnClickFavorite
import com.jdm.search.SearchContract.SearchEvent.OnClickSearch
import com.jdm.search.SearchContract.SearchViewState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchState by viewModel.viewState.collectAsStateWithLifecycle()

    SearchScreen(
        searchState = searchState,
        onFavoriteMovieCheckedChange = viewModel::setEvent,
        moreSearch = viewModel::setEvent,
        onClickSearch = viewModel::setEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchState: SearchViewState,
    onFavoriteMovieCheckedChange: (SearchEvent) -> Unit,
    moreSearch: (SearchEvent) -> Unit,
    onClickSearch: (SearchEvent) -> Unit
) {
    val numberOfItemsByRow = LocalConfiguration.current.screenWidthDp / 2

    val listState = rememberLazyGridState()

    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collectLatest { index ->
            if (index != null && index > searchState.movies.size - 5) {
                moreSearch(MoreSearch)
            }
        }
    }
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = JdmTheme.colors.white
    ) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            SearchTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(56.dp),
                hint = "검색어를 입력하세요.",
                onClickSearch = { keyword ->
                    onClickSearch(OnClickSearch(keyword))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                modifier = Modifier.background(JdmTheme.colors.white),
                columns = GridCells.Adaptive(minSize = numberOfItemsByRow.dp),
                state = listState
            ) {
                searchCard(
                    searchState = searchState,
                    onFavoriteMovieCheckedChange = {
                        onFavoriteMovieCheckedChange(OnClickFavorite(it))
                    }
                )
            }
        }
    }
}

fun LazyGridScope.searchCard(
    searchState: SearchViewState,
    onFavoriteMovieCheckedChange: (Movie) -> Unit,
) {
    itemsIndexed(
        items = searchState.movies,
    ) { index, movie ->
        MovieCard(
            movie = movie,
            onClickIcon = { movie ->
                onFavoriteMovieCheckedChange(movie)
            }
        )
    }
}

@Composable
fun FavoriteToggleButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    HToggleButton(
        checked = isFavorite,
        onCheckedChange = { onClick() },
        icon = {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "UnFavorite"
            )
        },
        checkedIcon = {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite"
            )
        }
    )
}

@Composable
fun MovieCard(
    movie: Movie,
    onClickIcon: (Movie) -> Unit,
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = movie.poster,
        onState = { state ->
            isLoading = state is AsyncImagePainter.State.Loading
            isError = state is AsyncImagePainter.State.Error
        }
    )
    val isLocalInspection = LocalInspectionMode.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = JdmTheme.colors.white,
            contentColor = JdmTheme.colors.black
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
        ) {
            if (isLoading) {
                // Display a progress bar while loading
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp),
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }

            Image(
                modifier = Modifier
                    .matchParentSize(),
                contentScale = ContentScale.FillBounds,
                painter = if (isError.not() && !isLocalInspection) {
                    imageLoader
                } else {
                    painterResource(R.drawable.ic_help_gray600)
                },
                contentDescription = "Movie Thumbnail",
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(vertical = 4.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                modifier = Modifier.weight(1.0f),
                text = movie.title,
                style = JdmTheme.typography.L_S,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis

            )
            FavoriteToggleButton(
                isFavorite = movie.isFavorite.value,
                onClick = { onClickIcon(movie) }
            )

        }

    }
}
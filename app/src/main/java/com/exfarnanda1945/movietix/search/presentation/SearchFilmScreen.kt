package com.exfarnanda1945.movietix.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.exfarnanda1945.movietix.R
import com.exfarnanda1945.movietix.core.Constants.BASE_URL_IMAGE
import com.exfarnanda1945.movietix.home.banner.presentation.ShimmerEffect
import com.exfarnanda1945.movietix.search.domain.SearchFilm
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchFilmScreen(toDetail: (Int) -> Unit, onBack: () -> Unit, modifier: Modifier = Modifier) {
    val searchViewModel = koinViewModel<SearchFilmViewModel>()
    val query by searchViewModel.query.collectAsStateWithLifecycle()
    val isSearching by searchViewModel.isSearching.collectAsStateWithLifecycle()
    val searchResult by searchViewModel.listFilm.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    KoinContext {
        Scaffold(topBar = {
            Row(
                modifier = Modifier.padding(top = 30.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    Modifier.clickable {
                        onBack()
                    })
                OutlinedTextField(
                    value = query,
                    onValueChange = {
                        searchViewModel.onEvent(SearchEvent.OnUserTyping(it))
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                        )
                    },
                    suffix = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Clear",
                            modifier = Modifier.clickable {
                                searchViewModel.onEvent(SearchEvent.OnUserClearTextField)
                            })
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                )
            }
        }, modifier = modifier) { padding ->
            if (isSearching) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xff0B84FF))
                }
            } else if (searchResult.data.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White), contentAlignment = Alignment.Center
                ) {
                    Text(text = if (searchResult.errorMsg != null) searchResult.errorMsg.toString() else "Film Not Found!")
                }
            } else {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(padding)
                        .padding(top = 10.dp)
                        .verticalScroll(scrollState),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    (1..searchResult.data.size).forEach { index ->
                        SearchFilmCard(film = searchResult.data[index - 1], onClick = {
                            toDetail(it)
                        })
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchFilmScreenPreview() {
    SearchFilmScreen({}, {})
}

@Composable
fun SearchFilmCard(film: SearchFilm, onClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = { onClick(film.movieId) }, modifier = modifier
            .width(180.dp)
            .height(220.dp)
            .clickable {
                onClick(film.movieId)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GlideImage(
                imageModel = { BASE_URL_IMAGE + film.image },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds
                ),
                loading = {
                    ShimmerEffect()
                },
                failure = {
                    GlideImage(
                        imageModel = { R.drawable.image_broken },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.FillBounds
                        ),
                    )

                }

            )
        }
    }
}
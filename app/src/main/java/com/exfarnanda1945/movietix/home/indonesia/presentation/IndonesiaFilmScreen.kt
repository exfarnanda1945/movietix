package com.exfarnanda1945.movietix.home.indonesia.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.exfarnanda1945.movietix.core.Constants.BASE_URL_IMAGE
import com.exfarnanda1945.movietix.home.shared_discovery_film.domain.DiscoveryFilm
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IndonesiaFilmScreen(
    title: String,
    modifier: Modifier = Modifier,
    genreId: Int? = null,
    originCountry: String? = null,
) {
    val filmViewModel = koinViewModel<IndonesiaFilmViewModel>()
    val state by filmViewModel.filmState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        filmViewModel.load(genreId = genreId, originCountry = originCountry)
    }

    KoinContext {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = title,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(10.dp))
            if (state.isLoading || state.data.isEmpty()) {
                Text(text = "Loading...")
            } else {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    (1..4).forEach { index ->
                        FilmCard(film = state.data[index - 1])
                    }
                }
            }

        }
    }
}

@Composable
fun FilmCard(film: DiscoveryFilm, modifier: Modifier = Modifier) {
    Card(
        onClick = { /*TODO*/ }, modifier = modifier
            .width(180.dp)
            .height(220.dp)
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
                )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun IndonesiaFilmScreenPreview() {
    IndonesiaFilmScreen("")
}
package com.exfarnanda1945.movietix.home.war_film.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.exfarnanda1945.movietix.R
import com.exfarnanda1945.movietix.home.banner.presentation.ShimmerEffect
import com.exfarnanda1945.movietix.home.indonesia.presentation.FilmCard
import com.exfarnanda1945.movietix.home.indonesia.presentation.IndonesiaFilmScreen
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WarFilmScreen(
    title: String,
    onDetail: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    genreId: Int? = null,
    originCountry: String? = null,
) {
    val filmViewModel = koinViewModel<WarFilmViewModel>()
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
            if (state.isLoading) {
                WarFilmLoading()
            } else if (state.errorMsg != null || state.data.isEmpty()) {
                WarFilmError()
            } else {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    (1..4).forEach { index ->
                        FilmCard(film = state.data[index - 1], onClick = {
                            onDetail(it)
                        })
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WarFilmError() {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        (1..4).forEach { index ->
            GlideImage(
                imageModel = {
                    R.drawable.image_broken
                }, modifier = Modifier
                    .width(180.dp)
                    .height(220.dp)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WarFilmLoading() {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        (1..4).forEach { index ->
            ShimmerEffect(
                modifier = Modifier
                    .width(180.dp)
                    .height(220.dp)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun IndonesiaFilmScreenPreview() {
    IndonesiaFilmScreen(title = "", onDetail = {})
}
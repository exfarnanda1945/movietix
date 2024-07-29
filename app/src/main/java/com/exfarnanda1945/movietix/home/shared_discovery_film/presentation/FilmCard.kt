package com.exfarnanda1945.movietix.home.shared_discovery_film.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.exfarnanda1945.movietix.R
import com.exfarnanda1945.movietix.core.Constants.BASE_URL_IMAGE
import com.exfarnanda1945.movietix.home.banner.presentation.ShimmerEffect
import com.exfarnanda1945.movietix.home.shared_discovery_film.domain.DiscoveryFilm
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun FilmCard(film: DiscoveryFilm, onClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = { onClick(film.movieId) }, modifier = modifier
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilmLoading() {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        (1..4).forEach { _ ->
            ShimmerEffect(
                modifier = Modifier
                    .width(180.dp)
                    .height(220.dp)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilmError() {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        (1..4).forEach { _ ->
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
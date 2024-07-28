package com.exfarnanda1945.movietix.home.top_genre.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.exfarnanda1945.movietix.home.banner.presentation.ShimmerEffect
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

@Composable
fun TopGenreScreen(modifier: Modifier = Modifier) {
    val genreViewModel = koinViewModel<TopGenreScreenViewModel>()
    val state by genreViewModel.genreState.collectAsStateWithLifecycle()

    KoinContext {
        Column(modifier) {
            Text(
                text = "Top Genre For You", style = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.Bold
                ), modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
            )
            if (state.isLoading) {
                GenreLoading()
            } else if (state.errorMsg != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp), contentAlignment = Alignment.Center
                ) {
                    Text(text = state.errorMsg.toString())
                }
            } else {
                val genre = state.data

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        GenreCard(genre[0].name)
                        GenreCard(genre[1].name)
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        GenreCard(genre[2].name)
                        GenreCard(genre[3].name)
                    }

                }
            }
        }
    }
}

@Composable
fun GenreLoading(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ShimmerEffect(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            ShimmerEffect(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ShimmerEffect(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            ShimmerEffect(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun GenreCard(title: String) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = title, style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                ), modifier = Modifier.padding(vertical = 15.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TopGenreScreenPreview() {
    TopGenreScreen()
}
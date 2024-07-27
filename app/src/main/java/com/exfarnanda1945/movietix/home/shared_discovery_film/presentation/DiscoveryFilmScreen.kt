package com.exfarnanda1945.movietix.home.shared_discovery_film.presentation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exfarnanda1945.movietix.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DiscoveryFilmScreen(title: String, modifier: Modifier = Modifier) {
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
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            (1..4).forEach { _ ->
                FilmCard()
            }
        }
    }
}

@Composable
fun FilmCard(modifier: Modifier = Modifier) {
    Card(
        onClick = { /*TODO*/ }, modifier = modifier
            .width(180.dp)
            .height(220.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(
                        id = R.drawable.example_poster_film
                    ), contentScale = ContentScale.FillBounds
                )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DiscoveryFilmScreenPreview() {
    DiscoveryFilmScreen("")
}
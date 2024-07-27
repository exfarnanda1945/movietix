package com.exfarnanda1945.movietix.home.top_rated.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exfarnanda1945.movietix.R

@Composable
fun TopRatedFilmScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 5.dp)
    ) {
        Text(
            text = "Top Rated Film", style = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            items(8) {
                RowCardFilm()
            }
        }
    }
}

@Composable
fun RowCardFilm(modifier: Modifier = Modifier) {
    Card {
        Column(
            modifier = modifier
                .height(190.dp)
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.example_poster_film),
                contentScale = ContentScale.FillBounds,
                contentDescription = "item",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.65f)
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Inside Out 2", style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp, textAlign = TextAlign.Center,
                    ),
                    maxLines = 2, overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = "Like")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "8.5", style = TextStyle(fontSize = 16.sp))
                }
            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TopRatedFilmScreenPreview() {
    TopRatedFilmScreen()
}
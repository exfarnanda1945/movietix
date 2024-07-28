package com.exfarnanda1945.movietix.home.top_rated.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.exfarnanda1945.movietix.core.Constants.BASE_URL_IMAGE
import com.exfarnanda1945.movietix.home.top_rated.domain.TopRatedFilm
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

@Composable
fun TopRatedFilmScreen(modifier: Modifier = Modifier) {
    val topRatedViewModel = koinViewModel<TopRatedFilmViewModel>()
    val state by topRatedViewModel.topRatedState.collectAsStateWithLifecycle()

    KoinContext {
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
                if (state.isLoading) {
                    item {
                        Text(text = "Loading")
                    }
                } else {
                    items(state.data.size) { index ->
                        RowCardFilm(item = state.data[index])
                    }
                }
            }
        }
    }
}

@Composable
fun RowCardFilm(item: TopRatedFilm, modifier: Modifier = Modifier) {
    Card {
        Column(
            modifier = modifier
                .height(190.dp)
                .width(130.dp)
        ) {
            GlideImage(
                imageModel = {
                    BASE_URL_IMAGE + item.image
                },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds
                ),
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
                    text = item.title, style = TextStyle(
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
                    Text(text = item.rated.toString(), style = TextStyle(fontSize = 16.sp))
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
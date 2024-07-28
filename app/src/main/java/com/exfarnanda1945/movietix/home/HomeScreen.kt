package com.exfarnanda1945.movietix.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exfarnanda1945.movietix.R
import com.exfarnanda1945.movietix.home.banner.presentation.BannerScreen
import com.exfarnanda1945.movietix.home.indonesia.presentation.IndonesiaFilmScreen
import com.exfarnanda1945.movietix.home.top_genre.presentation.TopGenreScreen
import com.exfarnanda1945.movietix.home.top_rated.presentation.TopRatedFilmScreen
import com.exfarnanda1945.movietix.home.war_film.presentation.WarFilmScreen
import org.koin.compose.KoinContext

@Composable
fun HomeScreen(
    onDetail: (id: Int) -> Unit,
    navigateToSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    KoinContext {
        Scaffold { padding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(scrollState)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Hello, ", style = TextStyle(
                                fontSize = 18.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "Mate", style = TextStyle(
                                fontSize = 24.sp
                            )
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_movie),
                        contentDescription = "Logo",
                        modifier = Modifier.size(36.dp),
                        tint = Color.Unspecified
                    )
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        navigateToSearch()
                    }
                    .background(Color(0xffE1E2EC)), contentAlignment = Alignment.CenterStart) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                BannerScreen(onDetail = onDetail)
                Spacer(modifier = Modifier.height(10.dp))
                TopGenreScreen()
                Spacer(modifier = Modifier.height(15.dp))
                TopRatedFilmScreen(onDetail = onDetail)
                Spacer(modifier = Modifier.height(15.dp))
                IndonesiaFilmScreen(
                    "Best Indonesia Film",
                    originCountry = "ID",
                    onDetail = onDetail
                )
                Spacer(modifier = Modifier.height(15.dp))
                WarFilmScreen("War", genreId = 10752, onDetail = onDetail)
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPrev() {
    HomeScreen({}, {})
}

package com.exfarnanda1945.movietix.home

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.TextField
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
import com.exfarnanda1945.movietix.home.shared_discovery_film.presentation.DiscoveryFilmScreen
import com.exfarnanda1945.movietix.home.top_genre.presentation.TopGenreScreen
import com.exfarnanda1945.movietix.home.top_rated.presentation.TopRatedFilmScreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
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
                        text = "My Name", style = TextStyle(
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

            TextField(
                value = "",
                onValueChange = {},
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp)
                    .clip(RoundedCornerShape(20.dp)),
            )
            Spacer(modifier = Modifier.height(10.dp))
            BannerScreen()
            Spacer(modifier = Modifier.height(10.dp))
            TopGenreScreen()
            Spacer(modifier = Modifier.height(15.dp))
            TopRatedFilmScreen()
            Spacer(modifier = Modifier.height(15.dp))
            DiscoveryFilmScreen("Best Indonesia Film")
            Spacer(modifier = Modifier.height(15.dp))
            DiscoveryFilmScreen("Documentary")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPrev() {
    HomeScreen()
}

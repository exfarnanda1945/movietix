package com.exfarnanda1945.movietix.home.top_genre.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopGenreScreen(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Top Categories", style = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.Bold
            ), modifier = Modifier.padding(10.dp)
        )
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
                GenreCard("Action")
                GenreCard("Super Heroes")
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                GenreCard("Documentary")
                GenreCard("Comedy")
            }

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
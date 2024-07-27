package com.exfarnanda1945.movietix.home.banner.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exfarnanda1945.movietix.R

@Composable
fun BannerScreen(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(4) {
            Box(
                modifier = Modifier
                    .fillParentMaxWidth(0.9f)
                    .fillParentMaxHeight()
                    .clip(RoundedCornerShape(10.dp))
                    .paint(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentScale = ContentScale.FillBounds
                    )
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BannerScreenPreview() {
    BannerScreen()
}
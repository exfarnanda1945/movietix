package com.exfarnanda1945.movietix.home.banner.presentation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.exfarnanda1945.movietix.core.Constants.BASE_URL_IMAGE
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext

@Composable
fun BannerScreen(modifier: Modifier = Modifier) {
    val bannerViewModel = koinViewModel<BannerViewModel>()
    val state by bannerViewModel.bannerState.collectAsStateWithLifecycle()

    KoinContext {
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .height(290.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (state.isLoading) {
                items(2) {
                    ShimmerEffect(
                        modifier = Modifier
                            .fillParentMaxWidth(0.94f)
                            .fillParentMaxHeight()
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            } else {
                items(state.data.size) { index ->
                    Box(
                        modifier = Modifier
                            .fillParentMaxWidth(0.9f)
                            .fillParentMaxHeight()
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        GlideImage(
                            imageModel = { BASE_URL_IMAGE + state.data[index].image },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.FillBounds
                            ),
                            loading = {
                                ShimmerEffect()
                            })
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BannerScreenPreview() {
    BannerScreen()
}


@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush)
    )
}
package com.exfarnanda1945.movietix.detail.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.exfarnanda1945.movietix.core.Constants.BASE_URL_IMAGE
import com.exfarnanda1945.movietix.core.Constants.BASE_URL_VIDEO
import com.exfarnanda1945.movietix.detail.domain.VideoItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext
import kotlin.math.round

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(id: Int, onBack: () -> Unit, modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val scrollState = rememberScrollState()
    val detailViewModel = koinViewModel<DetailFilmViewModel>()

    LaunchedEffect(true) {
        detailViewModel.load(id)
    }

    val state by detailViewModel.detailState.collectAsStateWithLifecycle()
    val data = state.data
    val images = data.posters.toMutableList()

    val videoUrl = getVideoUrl(data.videos)

    KoinContext {
        Scaffold(topBar = {
            TopAppBar(title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        Modifier.clickable { onBack() })
                    Text(text = "Detail Film")
                }
            })
        }) { padding ->
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Loading...", textAlign = TextAlign.Center)
                    }
                }
            } else if (state.errorMsg != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = state.errorMsg.toString(), textAlign = TextAlign.Center)
                }
            } else {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                        .padding(padding)
                ) {
                    GlideImage(
                        imageModel = { BASE_URL_IMAGE + data.image }, imageOptions = ImageOptions(
                            contentScale = ContentScale.FillBounds
                        ),
                        modifier = Modifier
                            .height(450.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = data.title,
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1.5f)
                        )
                        Text(
                            text = "Released\n ${data.releaseDate}", style = TextStyle(
                                fontSize = 13.sp
                            ),
                            modifier = Modifier.weight(0.5f), textAlign = TextAlign.End
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = data.genres.joinToString { it.name },
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Rating",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(imageVector = Icons.Filled.Star, contentDescription = "Rating")
                        Text(
                            text = (round(data.rate * 10.0) / 10.0).toString(), style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = data.description,
                        style = TextStyle(fontSize = 14.sp),
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    ) {
                        Text(
                            text = "Producer", style = TextStyle(
                                fontSize = 16.sp, fontWeight = FontWeight.SemiBold
                            )
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(data.companies.size) { index ->
                                GlideImage(
                                    imageModel = { BASE_URL_IMAGE + data.companies[index].logo },
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(60.dp),
                                    imageOptions = ImageOptions(
                                        contentScale = ContentScale.FillBounds
                                    )
                                )
                            }
                        }

                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                    ) {
                        Text(
                            text = "Trailer", style = TextStyle(
                                fontSize = 16.sp, fontWeight = FontWeight.SemiBold
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        VidePlayer(
                            url = videoUrl,
                            lifecycleOwner = lifecycleOwner,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp, start = 10.dp, end = 10.dp)
                    ) {
                        Text(
                            text = "Images", style = TextStyle(
                                fontSize = 16.sp, fontWeight = FontWeight.SemiBold
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            (1..images.size).forEach { index ->
                                FilmImageCard(images[index - 1])
                            }
                        }
                    }
                }
            }
        }
    }

}

fun getVideoUrl(videos: List<VideoItem>): String {
    if (videos.isEmpty()) return ""

    val trailer = videos.find { it.type == "Trailer" }

    if (trailer != null) {
        return trailer.key
    }

    val teaser = videos.find { it.type == "Teaser" }

    if (teaser != null) {
        return teaser.key
    }

    return videos.first().key
}

@Composable
fun FilmImageCard(item: String, modifier: Modifier = Modifier) {
    Card(
        onClick = {}, modifier = modifier
            .width(180.dp)
            .height(220.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GlideImage(
                imageModel = { BASE_URL_IMAGE + item },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds
                ),
            )
        }
    }
}

@Composable
fun VidePlayer(url: String, lifecycleOwner: LifecycleOwner, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        AndroidView(factory = { context ->
            YouTubePlayerView(context).apply {
                lifecycleOwner.lifecycle.addObserver(this)
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.loadVideo(BASE_URL_VIDEO + url, 0f)
                    }
                })
            }
        })
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailSectionScreenPreview() {
    DetailScreen(1, {})
}
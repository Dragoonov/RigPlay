package com.moonlightbutterfly.rigplay.gamedetails.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moonlightbutterfly.rigplay.gamedetails.model.GameDetailsItem
import com.moonlightbutterfly.rigplay.gamelist.view.LoadingBar
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch

@Composable
fun MainLayout(
    game: GameDetailsItem,
    isLoading: Boolean
) {
    MainDetails(game, isLoading)
}

@Composable
fun MainDetails(
    game: GameDetailsItem,
    isLoading: Boolean
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Box {
        LazyColumn {
            item {
                KamelImage(
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    resource = asyncPainterResource(data = game.imageUrl),
                    contentDescription = "Game",
                    onLoading = { progress -> CircularProgressIndicator(progress) },
                    onFailure = { exception ->
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = exception.message.toString(),
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                )
                Title(game.title)
                Text(game.description, modifier = Modifier.padding(8.dp), maxLines = 10, overflow = TextOverflow.Ellipsis)
                SecondaryData(game)
            }
        }
        if (isLoading) {
            LoadingBar()
        }
    }
}

@Composable
fun Title(text: String) {
    Text(
        text,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp, start = 16.dp)
    )
}

@Composable
fun SecondaryData(game: GameDetailsItem) {
    Section(name = "Info") {
        Data(
            title = "Developers",
            value = game.developers.map { it.name }.toString(),
        )
        Data(
            title = "Website",
            value = game.website,
        )
        Data(
            title = "Platforms",
            value = game.platforms.map { it.name }.toString()
        )
        Data(
            title = "Genres",
            value = game.genres.map { it.name }.toString()
        )
    }
}

@Composable
fun Section(name: String, block: @Composable () -> Unit) {
    Text(
        text = name,
        fontSize = 32.sp,
        modifier = Modifier.padding(bottom = 24.dp, start = 8.dp)
    )
    block()
}

@Composable
fun Data(title: String, value: String) {
    Row(
        Modifier
            .height(64.dp)
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = value)
    }
}
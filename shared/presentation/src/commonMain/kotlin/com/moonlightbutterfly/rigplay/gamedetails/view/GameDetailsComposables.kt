package com.moonlightbutterfly.rigplay.gamedetails.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.moonlightbutterfly.rigplay.SharedRes
import com.moonlightbutterfly.rigplay.gamedetails.model.GameDetailsItem
import com.moonlightbutterfly.rigplay.gamelist.view.LoadingBar
import dev.icerock.moko.resources.compose.stringResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun GameDetailsMainView(
    view: GameDetailsView
) {
    val game by view.models.subscribeAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    Box {
        LazyColumn {
            item {
                KamelImage(
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    resource = asyncPainterResource(data = game.gameDetails.imageUrl),
                    contentDescription = "Game",
                    onLoading = { progress -> CircularProgressIndicator(progress) },
                    onFailure = { exception ->
                        LaunchedEffect(Unit) {
                            snackbarHostState.showSnackbar(
                                message = exception.message.toString(),
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                )
                Image(
                    imageVector = if (game.gameDetails.isLiked) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        view.onGameLikeChanged(game.gameDetails.isLiked.not())
                    }
                )
                Title(game.gameDetails.title)
                Text(game.gameDetails.description, modifier = Modifier.padding(8.dp), maxLines = 10, overflow = TextOverflow.Ellipsis)
                SecondaryData(game.gameDetails)
            }
        }
        if (game.isLoading) {
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
    Section(name = stringResource(SharedRes.strings.info)) {
        Data(
            title = stringResource(SharedRes.strings.developers),
            value = game.developers.map { it.name }.toString(),
        )
        Data(
            title = stringResource(SharedRes.strings.developers),
            value = game.website,
        )
        Data(
            title = stringResource(SharedRes.strings.platforms),
            value = game.platforms.map { it.name }.toString()
        )
        Data(
            title = stringResource(SharedRes.strings.genres),
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
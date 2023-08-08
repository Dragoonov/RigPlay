package com.moonlightbutterfly.rigplay.gamelist.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moonlightbutterfly.rigplay.SharedRes
import com.moonlightbutterfly.rigplay.gamelist.model.GameListItem
import dev.icerock.moko.resources.compose.stringResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch

@Composable
fun MainLayout(
    onRefreshClicked: () -> Unit,
    games: List<GameListItem>,
    isLoading: Boolean
) {
    MainList(onRefreshClicked, games, isLoading)
}

@Composable
fun MainList(
    onRefreshClicked: () -> Unit,
    games: List<GameListItem>,
    isLoading: Boolean
) {
    Box {
        LazyColumn {
            items(games) {
                ListItem(GameListItem(it.title, it.imageUrl))
            }
        }
        Button(onClick = onRefreshClicked, modifier = Modifier.padding(start = 50.dp)) {
            Text(stringResource(SharedRes.strings.refresh))
        }
        if (isLoading) {
            LoadingBar()
        }
    }
}

@Composable
fun LoadingBar() {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
            )
        }
    }
}

@Composable
fun ListItem(item: GameListItem) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(20.dp)
    ) {
        KamelImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp).clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)),
            resource = asyncPainterResource(data = item.imageUrl),
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
        Text(text = item.title, modifier = Modifier.padding(start = 10.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
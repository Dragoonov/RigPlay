package com.moonlightbutterfly.rigplay.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            Text("Refresh")
        }
        if (isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun ListItem(item: GameListItem) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        KamelImage(
            modifier = Modifier.width(100.dp).height(100.dp).padding(start = 10.dp),
            resource = asyncPainterResource(data = item.imageUrl),
            contentDescription = "Profile",
            onLoading = { progress -> CircularProgressIndicator(progress) },
            onFailure = { exception ->
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = exception.message.toString(),
                        actionLabel = "Hide",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        )
        Text(text = item.title, modifier = Modifier.padding(end = 10.dp))
    }
}
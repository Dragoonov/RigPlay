package com.moonlightbutterfly.rigplay.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moonlightbutterfly.rigplay.model.Game
import com.moonlightbutterfly.rigplay.data.repository.GamesDataSourceImpl
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch

@Composable
fun HomeLayout() {
    MainList()
}

@Composable
fun MainList() {

    val repository = GamesDataSourceImpl()
    val scope = rememberCoroutineScope()
    val (list, setList) = remember { mutableStateOf<List<Game>>(emptyList()) }

    scope.launch {
        setList(repository.getGames())
    }
    LazyColumn {
        items(list) {
            ListItem(GameListItem(it.title, it.imageUrl))
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
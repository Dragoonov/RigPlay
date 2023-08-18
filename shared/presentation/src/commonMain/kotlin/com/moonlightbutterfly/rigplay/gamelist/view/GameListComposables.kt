package com.moonlightbutterfly.rigplay.gamelist.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
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
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.moonlightbutterfly.rigplay.SharedRes
import com.moonlightbutterfly.rigplay.gamelist.model.GameListItem
import dev.icerock.moko.resources.compose.stringResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource


@Composable
fun GameListMainView(
    view: GameListView
) {
    val games by view.models.subscribeAsState()
    Box {
        LazyColumn {
            items(games.games) {
                ListItem(GameListItem(it.id, it.title, it.imageUrl), view::onGameSelected)
            }
        }
        Button(onClick = view::onRefreshClicked, modifier = Modifier.padding(start = 50.dp)) {
            Text(stringResource(SharedRes.strings.refresh))
        }
        if (games.isLoading) {
            LoadingBar()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
fun ListItem(item: GameListItem, onGameClickListener: (id: Int) -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable {
                onGameClickListener(item.id)
            }
    ) {
        KamelImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp).clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp)),
            resource = asyncPainterResource(data = item.imageUrl),
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
        Text(text = item.title, modifier = Modifier.padding(start = 10.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
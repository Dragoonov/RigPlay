package home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch

@Composable
fun HomeLayout() {
    MainList()
}

@Composable
fun MainList() {
    val list = remember {
        mutableStateListOf(
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
            GameListItem(
                "GTA V",
                "https://i.wpimg.pl/O/615x346/d.wpimg.pl/544065856--405976119/grand-theft-auto-v-gta-v.jpg"
            ),
        )
    }
    LazyColumn {
        items(list) {
            ListItem(it)
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
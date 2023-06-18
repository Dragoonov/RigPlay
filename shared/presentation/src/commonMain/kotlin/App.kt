import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import home.HomeLayout
@Composable
fun App() {
    MaterialTheme {
        HomeLayout()
    }
}

expect fun getPlatformName(): String
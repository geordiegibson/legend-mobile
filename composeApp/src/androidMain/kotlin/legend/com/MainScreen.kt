package legend.com

import SportyClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val client = remember { SportyClient(createHttpClient(OkHttp.create())) }

    Scaffold(
        bottomBar = { NavBar(navController = navController) } // Keeps NavBar fixed at bottom
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AppNavigator(navController, client)
        }
    }
}
package legend.com

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
object Profile

@Serializable
object LandingScreen


@Composable
fun AppNavigator() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = LandingScreen, enterTransition = { EnterTransition.None }, exitTransition = { ExitTransition.None }) {
        composable<LandingScreen> { LandingScreen(navController) }
        composable<Profile> { Profile(navController) }

    }
}
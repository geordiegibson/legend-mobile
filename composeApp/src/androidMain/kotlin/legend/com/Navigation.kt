package legend.com

import SportyClient
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import legend.com.HomePage.Home

@Serializable
object Profile

@Serializable
object LandingScreen

@Serializable
object Trade

@Serializable
object Home

@Serializable
object Statistics

@Serializable
object Leagues

@Composable
fun AppNavigator(navController: NavHostController, client: SportyClient) {
    NavHost(navController = navController, startDestination = LandingScreen, enterTransition = { EnterTransition.None }, exitTransition = { ExitTransition.None }) {
        composable<LandingScreen> { LandingScreen(navController) }
        composable<Profile> { Profile() }
        composable<Trade> { Trade() }
        composable<Home> { Home(client) }
        composable<Statistics> { Statistics() }
        composable<Leagues> { Leagues() }

    }
}
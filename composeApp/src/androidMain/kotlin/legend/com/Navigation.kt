package legend.com

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
object Profile

@Serializable
object LandingScreen

@Serializable
object Trade

@Serializable
object Team

@Serializable
object Statistics

@Serializable
object Leagues

@Composable
fun AppNavigator(navController: NavHostController) {
    NavHost(navController = navController, startDestination = LandingScreen, enterTransition = { EnterTransition.None }, exitTransition = { ExitTransition.None }) {
        composable<LandingScreen> { LandingScreen(navController) }
        composable<Profile> { Profile() }
        composable<Trade> { Trade() }
        composable<Team> { Team() }
        composable<Statistics> { Statistics() }
        composable<Leagues> { Leagues() }

    }
}
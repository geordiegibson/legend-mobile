package legend.com

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun NavBar(modifier: Modifier = Modifier, navController: NavController) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick={navController.navigate(Team)}) { Text("Team")}
        Button(onClick={navController.navigate(Leagues)}) { Text("Leagues")}
        Button(onClick={navController.navigate(Statistics)}) { Text("Statistics")}
        Button(onClick={navController.navigate(Profile)}) { Text("Profile")}
    }
}

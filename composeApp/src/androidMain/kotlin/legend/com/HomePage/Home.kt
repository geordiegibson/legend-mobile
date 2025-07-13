package legend.com.HomePage

import SportyClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import legend.com.networking.Fixture
import legend.com.networking.TeamStanding
import legend.com.util.NetworkError
import legend.com.util.onError
import legend.com.util.onSuccess
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Home(client: SportyClient) {

    var ladderInformation by remember {
        mutableStateOf<List<TeamStanding>?>(null)
    }
    var drawInformation by remember {
        mutableStateOf<List<Fixture>?>(null)
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var errorMessage by remember {
        mutableStateOf<NetworkError?>(null)
    }

    LaunchedEffect(Unit) {
        isLoading = true
        client.fetchDraw()
            .onSuccess {
                drawInformation = it
                errorMessage = null
            }
            .onError {
                errorMessage = it
            }

        client.fetchLadder()
            .onSuccess {
                ladderInformation = it
            }
            .onError {
                errorMessage = it
            }
        isLoading = false
    }

    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 40.dp).fillMaxWidth()) {
        Text(
            text = "Greymouth",
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = "Canterbury Rugby League",
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        ladderInformation?.let { TeamHeader(it) }

        Spacer(modifier = Modifier.height(8.dp))

        errorMessage?.let {
            Text(
                text = it.name,
                color = Color.Red
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f).height(150.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                drawInformation?.takeIf { it.isNotEmpty() }?.let { UpcomingMatchCard(it) }
            }

            Column(
                modifier = Modifier.weight(1f).height(150.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                LastMatch()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        NextFiveTeamsRow()

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f).height(150.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RecentDallyM()
            }

            Column(
                modifier = Modifier.weight(1f).height(150.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                LastMatch()
            }
        }
    }
}


package legend.com.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import legend.com.R
import legend.com.getTeamById
import legend.com.networking.Fixture

@Composable
fun UpcomingMatchCard(upcomingFixtures: List<Fixture>) {

    val upcomingFixture = upcomingFixtures[0]
    val oppositionTeamId = if (upcomingFixture.AwayTeamId != 350577)
        upcomingFixture.AwayTeamId
    else
        upcomingFixture.HomeTeamId
    val oppositionTeam = getTeamById(oppositionTeamId)

    Column(
        modifier = Modifier.padding(top = 8.dp).fillMaxWidth()
    ) {
        Text(
            text = "Upcoming Match",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(oppositionTeam.colour)),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hornets),
                    contentDescription = "Hornets Logo",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

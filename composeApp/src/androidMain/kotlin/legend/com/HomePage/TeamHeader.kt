package legend.com.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import legend.com.R
import legend.com.networking.Fixture
import legend.com.networking.TeamStanding

@Composable
fun TeamHeader(ladderInformation: List<TeamStanding>, ) {

    val teamIndex = ladderInformation.indexOfFirst { it.Id == 848211 }
    val teamPosition = teamIndex + 1
    val teamPoints = ladderInformation[teamIndex].Total

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0A0E2A)),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Greymouth Greyhounds",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "ISC Premiership",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray)
                )
                Text(
                    text = "${teamPoints}pts  |  ${teamPosition}th",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.greymouth),
                contentDescription = "Team Logo",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit
            )
        }
    }
}

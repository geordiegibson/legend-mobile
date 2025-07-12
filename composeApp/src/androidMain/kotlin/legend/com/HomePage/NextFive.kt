package legend.com.HomePage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import legend.com.R

@Preview
@Composable
fun NextFiveTeamsRow() {
    val teams = listOf(
        Pair(R.drawable.hornets, "HOME"),
        Pair(R.drawable.linwood, "AWAY"),
        Pair(R.drawable.hornets, "HOME"),
        Pair(R.drawable.linwood, "AWAY"),
        Pair(R.drawable.hornets, "HOME")
    )

    Text(
        text = "Next Five",
        style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.SemiBold
        )
    )

    Spacer(modifier = Modifier.height(8.dp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color("#EFEFEF".toColorInt()))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            teams.forEach { team ->
                NextFiveSubcomponent(team)
            }
        }
    }
}


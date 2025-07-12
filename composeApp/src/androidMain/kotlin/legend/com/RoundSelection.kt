package legend.com

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoundSelector(
    rounds: List<String>,
    selectedIndex: Int,
    onRoundSelected: (Int) -> Unit
) {
    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(50.dp))
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(rounds) { index, round ->
            Text(
                text = round,
                modifier = Modifier
                    .clickable { onRoundSelected(index) }
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = if (index == selectedIndex) FontWeight.Bold else FontWeight.Normal,
                    color = if (index == selectedIndex) Color.Black else Color.Gray
                )
            )
        }
    }
}

@Preview
@Composable
fun RoundSelectorDemo() {
    var selectedRound by remember { mutableStateOf(2) }
    val rounds = listOf("Round 5", "Round 6", "Round 7", "Round 8", "Round 9")

    RoundSelector(
        rounds = rounds,
        selectedIndex = selectedRound,
        onRoundSelected = { selectedRound = it }
    )
}

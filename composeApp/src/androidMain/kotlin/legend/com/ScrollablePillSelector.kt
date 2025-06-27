package legend.com

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.onSizeChanged
import legend.com.Helpers.animateScrollAndCentralizeItem

@Composable
fun ScrollablePillSelector(
    rounds: List<String>,
    selectedIndex: Int,
    onRoundSelected: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    var barWidthPx by remember { mutableStateOf(0) }
    var selectedItemWidthPx by remember { mutableStateOf(0) }

    LaunchedEffect(selectedIndex, barWidthPx, selectedItemWidthPx) {
        listState.animateScrollAndCentralizeItem(selectedIndex)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(color = Color(0xFFF7F7F7), shape = RoundedCornerShape(50.dp))
            .padding(vertical = 10.dp)
            .onSizeChanged { size ->
                barWidthPx = size.width
            }
    ) {
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            itemsIndexed(rounds) { index, round ->
                val isSelected = index == selectedIndex
                Text(
                    text = round,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) Color.Black else Color.Gray,
                    modifier = Modifier
                        .clickable { onRoundSelected(index) }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .then(
                            if (isSelected) Modifier.onSizeChanged { size ->
                                selectedItemWidthPx = size.width
                            } else Modifier
                        )
                )
            }
        }
    }
}
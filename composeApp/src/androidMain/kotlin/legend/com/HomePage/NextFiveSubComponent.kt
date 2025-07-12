package legend.com.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import legend.com.R
import androidx.core.graphics.toColorInt

@Composable
fun NextFiveSubcomponent(team: Any) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.height(75.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.hornets),
            contentDescription = "Hornets Logo",
            modifier = Modifier.size(42.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "HOME",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = Color("#848484".toColorInt())
        )
    }
}

package legend.com

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Leagues() {
    Text("My Leagues", Modifier.padding(start = 24.dp).padding(top = 30.dp), fontSize = 24.sp, fontFamily = ralewayFamily)
}
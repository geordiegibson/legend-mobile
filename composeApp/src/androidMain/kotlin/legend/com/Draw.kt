package legend.com

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import legend.com.DAL.DrawRepositoryImpl
import legend.com.Fonts.interFamily
import legend.com.model.DrawModel
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.ui.platform.LocalContext

@Composable
fun Draw() {
    var drawData by remember { mutableStateOf<List<List<DrawModel>>>(emptyList()) }
    val scope = rememberCoroutineScope()
    val drawRepo = DrawRepositoryImpl()
    var selectedRound by remember { mutableStateOf(0) }
    var rounds by remember { mutableStateOf<List<String>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        scope.launch {
            drawData = drawRepo.getDrawData()
            rounds = List(drawData.size) { i -> ("Round ${i + 1}").toString() }
        }
    }

    Column() {
        Row(
            modifier = Modifier
                .padding(top = 30.dp, bottom = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Draw",
                modifier = Modifier.padding(start = 35.dp),
                fontSize = 30.sp,
                fontFamily = interFamily
            )
            Text(
                "17th April 2025",
                modifier = Modifier.padding(end = 15.dp),
                fontSize = 16.sp,
                fontFamily = interFamily
            )
        }

        if (drawData.isNotEmpty()) {
            ScrollablePillSelector(
                rounds = rounds,
                selectedIndex = selectedRound,
                onRoundSelected = { selectedRound = it }
            )

            AnimatedContent(
                targetState = drawData[selectedRound],
                transitionSpec = {
                    fadeIn(animationSpec = tween(400)) togetherWith fadeOut(animationSpec = tween(400))
                },
                label = "MatchListTransition"
            ) { matchList ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    items(matchList) { match ->
                        val team1LogoId = remember(match.team1LogoUrl) {
                            context.resources.getIdentifier(match.team1LogoUrl, "drawable", context.packageName)
                        }

                        val team2LogoId = remember(match.team2LogoUrl) {
                            context.resources.getIdentifier(match.team2LogoUrl, "drawable", context.packageName)
                        }

                        MatchCard(
                            team1Logo = painterResource(id = if (team1LogoId != 0) team1LogoId else R.drawable.ic_launcher_background),
                            team2Logo = painterResource(id = if (team2LogoId != 0) team2LogoId else R.drawable.ic_launcher_foreground),
                            time = match.time,
                            venue = match.venue
                        )
                    }
                }
            }
        }
    }

}
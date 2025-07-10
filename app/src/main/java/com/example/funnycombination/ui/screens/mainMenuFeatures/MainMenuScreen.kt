package com.example.funnycombination.ui.screens.mainMenuFeatures

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import com.example.funnycombination.ui.screens.components.FloatingEmojisBackground
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.funnycombination.ui.screens.components.FulledButton
import com.example.funnycombination.ui.screens.components.OutlinedButton
import com.example.funnycombination.ui.theme.*
import kotlin.system.exitProcess

@Composable
fun MainMenuScreen(
    onPlayClick: () -> Unit = {},
    onHighScoreClick: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingEmojisBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .weight(2f)
            ) {
                Text(
                    text = "Funny",
                    style = MaterialTheme.typography.custom.appTitle
                )
                Text(
                    text = "Combination",
                    style = MaterialTheme.typography.custom.appTitle.copy(
                        color = SunshineYellow
                    )
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .padding(bottom = 60.dp)
                    .weight(1f)
            ) {
                val gameEmojis = listOf("😂", "👍", "❤️", "🥳", "🤯")
                gameEmojis.forEach { emoji ->
                    Text(
                        text = emoji,
                        style = MaterialTheme.typography.custom.appSubtitle
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.weight(2f)
            ) {
                FulledButton(
                    text = "🎮 Play",
                    onClick = onPlayClick,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedButton(
                    text = "🏆 High Score",
                    onClick = onHighScoreClick,
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .weight(2f)
            ) {
                TextButton(
                    onClick = { /* TODO: Open Privacy Policy */ },
                ) {
                    Text(
                        text = "Privacy Policy",
                        style = MaterialTheme.typography.custom.smallButtonText
                    )
                }

                TextButton(
                    onClick = { exitProcess(0) }
                ) {
                    Text(
                        text = "Exit",
                        style = MaterialTheme.typography.custom.smallButtonText
                    )
                }
            }
        }
    }
}
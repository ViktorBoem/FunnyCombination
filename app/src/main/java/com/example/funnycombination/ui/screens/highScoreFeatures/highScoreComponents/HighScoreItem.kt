package com.example.funnycombination.ui.screens.highScoreFeatures.highScoreComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funnycombination.ui.screens.highScoreFeatures.HighScoreUiItem
import com.example.funnycombination.ui.theme.DeepCharcoal
import com.example.funnycombination.ui.theme.JuicyOrange
import com.example.funnycombination.ui.theme.custom

@Composable
fun HighScoreItem(item: HighScoreUiItem) {
    val medal = when (item.rank) {
        1 -> "🏆"
        2 -> "🥈"
        3 -> "🥉"
        else -> null
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = if (item.isBest) BorderStroke(2.dp, JuicyOrange) else null
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "#${item.rank}",
                style = MaterialTheme.typography.custom.levelIndicator,
                color = DeepCharcoal.copy(alpha = 0.5f),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${item.score}",
                    style = MaterialTheme.typography.custom.ScoreValue
                )
                Text(
                    text = item.date,
                    style = MaterialTheme.typography.custom.caption
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (medal != null) {
                Text(
                    text = medal,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
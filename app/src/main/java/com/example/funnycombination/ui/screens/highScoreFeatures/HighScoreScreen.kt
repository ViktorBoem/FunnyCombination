package com.example.funnycombination.ui.screens.highScoreFeatures

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.funnycombination.ui.screens.highScoreFeatures.highScoreComponents.HighScoreItem
import com.example.funnycombination.ui.theme.CrystalWhite
import com.example.funnycombination.ui.theme.JuicyOrange
import com.example.funnycombination.ui.theme.custom
import androidx.compose.material3.CenterAlignedTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HighScoreScreen(
    onBack: () -> Unit,
    viewModel: HighScoreViewModel = hiltViewModel()
) {
    val highScores by viewModel.highScores.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "High Scores",
                        style = MaterialTheme.typography.custom.cardTitle,
                        color = JuicyOrange
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = JuicyOrange
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = CrystalWhite,
                )
            )
        },
        containerColor = CrystalWhite
    ) { paddingValues ->
        if (highScores.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No scores yet!",
                    style = MaterialTheme.typography.custom.hint
                )
                Text(
                    text = "Play a game to see list your records!",
                    style = MaterialTheme.typography.custom.hint
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(highScores) { score ->
                    HighScoreItem(item = score)
                }
            }
        }
    }
}
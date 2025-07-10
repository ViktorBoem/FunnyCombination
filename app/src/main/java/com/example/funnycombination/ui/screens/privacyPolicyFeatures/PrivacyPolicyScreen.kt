package com.example.funnycombination.ui.screens.privacyPolicyFeatures

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.funnycombination.ui.screens.components.FloatingEmojisBackground
import com.example.funnycombination.ui.screens.privacyPolicyFeatures.privacyPolicyComponents.PolicySection
import com.example.funnycombination.ui.theme.CrystalWhite
import com.example.funnycombination.ui.theme.JuicyOrange
import com.example.funnycombination.ui.theme.custom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Privacy Policy",
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
                    containerColor = CrystalWhite
                )
            )
        },
        containerColor = CrystalWhite
    ) { paddingValues ->
        FloatingEmojisBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                ) {
                    Text(
                        text = "Last Updated: July 8, 2025",
                        style = MaterialTheme.typography.custom.caption
                    )

                    PolicySection(
                        title = "",
                        content = "Thank you for playing Funny Combination! This Privacy Policy explains how we collect, use, and share information about you when you use our game."
                    )
                    PolicySection(
                        title = "Information We Collect",
                        content = "Funny Combination is a simple game that stores your high scores locally on your device. We do not collect any personal information from you."
                    )
                    PolicySection(
                        title = "Local Storage",
                        content = "We use your device's local storage to save your high scores. This information never leaves your device and is not transmitted to our servers or any third parties."
                    )
                    PolicySection(
                        title = "Changes to This Policy",
                        content = "We may update this Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page."
                    )
                    PolicySection(
                        title = "Contact Us",
                        content = "If you have any questions about this Privacy Policy, you can contact us (e.g., via email at privacy@example.com)."
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
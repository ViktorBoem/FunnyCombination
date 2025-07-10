package com.example.funnycombination.ui.screens.privacyPolicyFeatures.privacyPolicyComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.funnycombination.ui.theme.custom

@Composable
fun PolicySection(title: String, content: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        if (title.isNotEmpty()) {
            Text(
                text = title,
                style = MaterialTheme.typography.custom.dialogTitle,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(
            text = content,
            style = MaterialTheme.typography.custom.dialogContent
        )
    }
}
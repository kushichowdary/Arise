package com.example.ui.water

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterTrackerScreen(
    viewModel: WaterTrackerViewModel,
    onNavigateBack: () -> Unit
) {
    val todayWater by viewModel.todayWater.collectAsStateWithLifecycle()
    val consumed = todayWater?.consumedAmount ?: 0
    val goal = todayWater?.goalAmount ?: 2000
    val progress = consumed.toFloat() / goal.toFloat()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Water Tracker") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Today's Intake",
                style = MaterialTheme.typography.headlineMedium
            )

            CircularProgressIndicator(
                progress = { progress.coerceIn(0f, 1f) },
                modifier = Modifier.size(200.dp),
                strokeWidth = 16.dp,
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primaryContainer,
            )

            Text(
                text = "$consumed / $goal ml",
                style = MaterialTheme.typography.titleLarge
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { viewModel.addWater(250) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("+ 250ml")
                }
                Button(
                    onClick = { viewModel.addWater(500) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("+ 500ml")
                }
            }
        }
    }
}

package com.example.ui.workout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutTrackerScreen(
    viewModel: WorkoutTrackerViewModel,
    onNavigateBack: () -> Unit
) {
    val workouts by viewModel.workouts.collectAsStateWithLifecycle()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Workout Tracker") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Workout")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(workouts) { workout ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = workout.type, style = MaterialTheme.typography.titleLarge)
                        Text(text = "Duration: ${workout.durationMinutes} mins | Calories: ${workout.calories} kcal", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Date: ${workout.date}", style = MaterialTheme.typography.bodySmall)
                        if (workout.notes.isNotBlank()) {
                            Text(text = workout.notes, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
        
        if (showAddDialog) {
            AddWorkoutDialog(
                onDismiss = { showAddDialog = false },
                onAdd = { type, duration, calories, notes ->
                    viewModel.addWorkout(type, duration, calories, notes)
                    showAddDialog = false
                }
            )
        }
    }
}

@Composable
fun AddWorkoutDialog(
    onDismiss: () -> Unit,
    onAdd: (String, Int, Int, String) -> Unit
) {
    var type by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Workout") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = type,
                    onValueChange = { type = it },
                    label = { Text("Workout Type") }
                )
                OutlinedTextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = { Text("Duration (mins)") }
                )
                OutlinedTextField(
                    value = calories,
                    onValueChange = { calories = it },
                    label = { Text("Calories Burned") }
                )
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onAdd(type, duration.toIntOrNull() ?: 0, calories.toIntOrNull() ?: 0, notes)
                },
                enabled = type.isNotBlank() && duration.isNotBlank()
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

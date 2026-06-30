package com.example.ui.study

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
fun StudyTrackerScreen(
    viewModel: StudyTrackerViewModel,
    onNavigateBack: () -> Unit
) {
    val sessions by viewModel.sessions.collectAsStateWithLifecycle()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Study Tracker") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Session")
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
            items(sessions) { session ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = session.subject, style = MaterialTheme.typography.titleLarge)
                        Text(text = "Duration: ${session.durationMinutes} mins", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Date: ${session.date}", style = MaterialTheme.typography.bodySmall)
                        if (session.notes.isNotBlank()) {
                            Text(text = session.notes, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
        
        if (showAddDialog) {
            AddStudySessionDialog(
                onDismiss = { showAddDialog = false },
                onAdd = { subject, duration, notes ->
                    viewModel.addStudySession(subject, duration, notes)
                    showAddDialog = false
                }
            )
        }
    }
}

@Composable
fun AddStudySessionDialog(
    onDismiss: () -> Unit,
    onAdd: (String, Int, String) -> Unit
) {
    var subject by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Study Session") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    label = { Text("Subject") }
                )
                OutlinedTextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = { Text("Duration (mins)") }
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
                    onAdd(subject, duration.toIntOrNull() ?: 0, notes)
                },
                enabled = subject.isNotBlank() && duration.isNotBlank()
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

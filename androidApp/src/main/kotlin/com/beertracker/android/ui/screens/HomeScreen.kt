package com.beertracker.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToStyles: () -> Unit,
    onNavigateToDriver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BeerTracker") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onNavigateToStyles,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Icon(Icons.Default.LocalBar, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Escolher Estilo")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNavigateToDriver,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Icon(Icons.Default.Person, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Motorista da Vez")
            }
        }
    }
}

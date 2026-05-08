package com.beertracker.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChosenDriverScreen(
    driverName: String,
    onPickAgain: () -> Unit,
    onGoHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.size(120.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Motorista da Vez",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = driverName,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onPickAgain,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Sortear Novamente")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onGoHome,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Início")
        }
    }
}

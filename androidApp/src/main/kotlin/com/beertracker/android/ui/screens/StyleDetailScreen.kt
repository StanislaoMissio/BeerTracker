package com.beertracker.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.beertracker.shared.model.Harmony
import com.beertracker.shared.model.Style

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyleDetailScreen(
    style: Style?,
    harmonies: List<Harmony>,
    isLoading: Boolean,
    error: String?,
    onBack: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(style?.nomeEstilo ?: "Detalhes") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        if (style == null) return@Scaffold

        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Description, contentDescription = null) },
                    text = { Text("Detalhes") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Restaurant, contentDescription = null) },
                    text = { Text("Harmonias") }
                )
            }

            when (selectedTab) {
                0 -> DetailTab(style)
                1 -> HarmonyTab(harmonies, isLoading, error)
            }
        }
    }
}

@Composable
private fun DetailTab(style: Style) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = style.image,
            contentDescription = style.nomeEstilo,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = style.nomeEstilo,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = style.descricao,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun HarmonyTab(
    harmonies: List<Harmony>,
    isLoading: Boolean,
    error: String?
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            error != null -> {
                Text(
                    text = error,
                    modifier = Modifier.align(Alignment.Center).padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
            harmonies.isEmpty() -> {
                Text(
                    text = "Nenhuma harmonia encontrada",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(harmonies) { harmony ->
                        HarmonyCard(harmony)
                    }
                }
            }
        }
    }
}

@Composable
private fun HarmonyCard(harmony: Harmony) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                model = harmony.imagem,
                contentDescription = harmony.nomeHarmonia,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = harmony.nomeHarmonia,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = harmony.descricao,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

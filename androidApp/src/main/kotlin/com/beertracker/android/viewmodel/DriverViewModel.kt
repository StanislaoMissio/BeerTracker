package com.beertracker.android.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DriverUiState(
    val drivers: List<String> = emptyList(),
    val selectedDriver: String? = null
)

class DriverViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DriverUiState())
    val uiState: StateFlow<DriverUiState> = _uiState.asStateFlow()

    fun addDriver(name: String) {
        if (name.isBlank()) return
        val current = _uiState.value.drivers
        _uiState.value = _uiState.value.copy(drivers = current + name)
    }

    fun removeDriver(index: Int) {
        val current = _uiState.value.drivers.toMutableList()
        if (index in current.indices) {
            current.removeAt(index)
            _uiState.value = _uiState.value.copy(drivers = current)
        }
    }

    fun pickRandomDriver(): String? {
        val drivers = _uiState.value.drivers
        if (drivers.isEmpty()) return null
        val index = (drivers.indices).random()
        val picked = drivers[index]
        _uiState.value = _uiState.value.copy(selectedDriver = picked)
        return picked
    }
}

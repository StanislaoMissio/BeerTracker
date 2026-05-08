package com.beertracker.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beertracker.shared.model.User
import com.beertracker.shared.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: String? = null
)

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            val result = repository.login(username, password)
            result.fold(
                onSuccess = {
                    _uiState.value = _uiState.value.copy(isLoading = false, isLoggedIn = true)
                },
                onFailure = {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = it.message ?: "Erro ao efetuar login"
                    )
                }
            )
        }
    }

    fun register(username: String, password: String, email: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            val result = repository.register(username, password, email)
            result.fold(
                onSuccess = {
                    _uiState.value = _uiState.value.copy(isLoading = false, isLoggedIn = true)
                },
                onFailure = {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = it.message ?: "Erro ao executar cadastro"
                    )
                }
            )
        }
    }

    fun resetState() {
        _uiState.value = AuthUiState()
    }
}

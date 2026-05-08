package com.beertracker.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beertracker.shared.model.Harmony
import com.beertracker.shared.model.Style
import com.beertracker.shared.repository.HarmonyRepository
import com.beertracker.shared.repository.StyleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class StyleListUiState(
    val isLoading: Boolean = false,
    val styles: List<Style> = emptyList(),
    val error: String? = null
)

data class StyleDetailUiState(
    val isLoading: Boolean = false,
    val harmonies: List<Harmony> = emptyList(),
    val error: String? = null
)

class StyleViewModel : ViewModel() {
    private val styleRepository = StyleRepository()
    private val harmonyRepository = HarmonyRepository()

    private val _listState = MutableStateFlow(StyleListUiState())
    val listState: StateFlow<StyleListUiState> = _listState.asStateFlow()

    private val _detailState = MutableStateFlow(StyleDetailUiState())
    val detailState: StateFlow<StyleDetailUiState> = _detailState.asStateFlow()

    private var cachedStyles: List<Style> = emptyList()

    fun loadStyles() {
        viewModelScope.launch {
            _listState.value = _listState.value.copy(isLoading = true, error = null)
            val result = styleRepository.getAllStyles()
            result.fold(
                onSuccess = { styles ->
                    cachedStyles = styles
                    _listState.value = _listState.value.copy(isLoading = false, styles = styles)
                },
                onFailure = {
                    _listState.value = _listState.value.copy(
                        isLoading = false,
                        error = it.message ?: "Erro ao carregar estilos"
                    )
                }
            )
        }
    }

    fun getStyleAt(index: Int): Style? = cachedStyles.getOrNull(index)

    fun loadHarmonies(style: Style) {
        viewModelScope.launch {
            _detailState.value = _detailState.value.copy(isLoading = true, error = null)
            val result = harmonyRepository.getHarmonies(style)
            result.fold(
                onSuccess = { harmonies ->
                    _detailState.value = _detailState.value.copy(isLoading = false, harmonies = harmonies)
                },
                onFailure = {
                    _detailState.value = _detailState.value.copy(
                        isLoading = false,
                        error = it.message ?: "Erro ao carregar harmonias"
                    )
                }
            )
        }
    }
}

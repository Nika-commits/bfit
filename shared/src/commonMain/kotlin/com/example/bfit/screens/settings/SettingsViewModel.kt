package com.example.bfit.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bfit.data.repository.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    fun toggleTheme() {
        viewModelScope.launch {
            settingsRepository.toggleTheme()
        }
    }

    fun testText(): String = "This is a Test"
}
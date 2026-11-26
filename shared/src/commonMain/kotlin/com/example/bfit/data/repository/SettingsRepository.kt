package com.example.bfit.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

class SettingsRepository(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun toggleTheme() {

    }
}
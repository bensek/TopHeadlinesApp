package com.bensek.topheadlines.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _isLoggedIn = MutableStateFlow(savedStateHandle[LOGGED_IN_KEY] ?: false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    fun updateLogin(loggedIn: Boolean) {
        _isLoggedIn.value = loggedIn
        savedStateHandle[LOGGED_IN_KEY] = loggedIn
    }

    companion object {
        private const val LOGGED_IN_KEY = "logged_in_key"
    }
}
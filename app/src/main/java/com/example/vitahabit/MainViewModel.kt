package com.example.vitahabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface AppStartState {
    object Loading : AppStartState
    object LoggedIn : AppStartState
    object LoggedOut : AppStartState
}

class MainViewModel : ViewModel() {
    private val _startState = MutableStateFlow<AppStartState>(AppStartState.Loading)
    val startState = _startState.asStateFlow()
    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
//            delay(1500) // Simulate a network check

            // In a real app, you would replace this with a real check, for example:
            // val user = FirebaseAuth.getInstance().currentUser
            // if (user != null) {
            //     _startState.value = AppStartState.LoggedIn
            // } else {
            //     _startState.value = AppStartState.LoggedOut
            // }

            // Default to logged out for now
            _startState.value = AppStartState.LoggedIn
        }
    }
}
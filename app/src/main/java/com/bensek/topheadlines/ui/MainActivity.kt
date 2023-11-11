package com.bensek.topheadlines.ui

import BiometricAuthListener
import BiometricUtil
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.bensek.topheadlines.R
import com.bensek.topheadlines.ui.navigation.TopHeadlinesApp
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), BiometricAuthListener {
    private val mainViewModel: MainActivityViewModel by inject()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loggedIn by mainViewModel.isLoggedIn.collectAsState()

            if (!loggedIn) {
                startBiometricLogin()
            } else {
                val windowSizeClass = calculateWindowSizeClass(this)
                TopHeadlinesApp(windowSizeClass)
            }
        }
    }

    private fun startBiometricLogin() {
        if (BiometricUtil.isBiometricReady(this)) {
            BiometricUtil.showBiometricPrompt(
                activity = this,
                listener = this,
                cryptoObject = null
            )
        } else {
            mainViewModel.updateLogin(true)
        }
    }

    override fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult) {
        mainViewModel.updateLogin(true)
    }

    override fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String) {
        if (BiometricUtil.isBiometricReady(this)) {
            mainViewModel.updateLogin(false)
            Toast.makeText(this, getString(R.string.login_cancelled), Toast.LENGTH_LONG).show()
            finish()
        }
    }
}

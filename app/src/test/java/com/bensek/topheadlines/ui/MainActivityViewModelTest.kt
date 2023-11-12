package com.bensek.topheadlines.ui

import androidx.lifecycle.SavedStateHandle
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainActivityViewModelTest {

    @MockK(relaxed = true)
    lateinit var mockSavedStateHandle: SavedStateHandle

    private lateinit var viewModel: MainActivityViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun should_returnFalse_when_savedStateNull() = runTest {
        setupViewModel(null)

        val result = viewModel.isLoggedIn.first()

        assertEquals(false, result)
    }

    @Test
    fun should_returnTrue_when_savedStateTrue() = runTest {
        setupViewModel(true)

        val result = viewModel.isLoggedIn.first()

        assertEquals(true, result)
    }

    @Test
    fun should_returnTrue_when_loggedInTrue() = runTest {
        setupViewModel(null)

        val loggedIn = true
        viewModel.updateLogin(loggedIn)

        verify { mockSavedStateHandle[MainActivityViewModel.LOGGED_IN_KEY] = loggedIn }
        assertEquals(loggedIn, viewModel.isLoggedIn.first())
    }

    @Test
    fun should_returnTrue_when_loggedInFalse() = runTest {
        setupViewModel(null)

        val loggedIn = false
        viewModel.updateLogin(loggedIn)

        verify { mockSavedStateHandle[MainActivityViewModel.LOGGED_IN_KEY] = loggedIn }
        assertEquals(loggedIn, viewModel.isLoggedIn.first())
    }

    private fun setupViewModel(loggedInValue: Boolean?) {
        every { mockSavedStateHandle.get<Boolean?>(MainActivityViewModel.LOGGED_IN_KEY) } returns loggedInValue
        viewModel = MainActivityViewModel(mockSavedStateHandle)
    }
}
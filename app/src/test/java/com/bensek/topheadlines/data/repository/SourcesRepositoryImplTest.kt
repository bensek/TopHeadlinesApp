package com.bensek.topheadlines.data.repository

import com.bensek.topheadlines.domain.model.Source
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SourcesRepositoryImplTest {
    private lateinit var sourcesRepository: SourcesRepositoryImpl

    @Before
    fun setUp() {
        sourcesRepository = mockk()
    }

    @Test
    fun getCurrentSource_shouldReturnBBCNewsSource() {
        val mockedSource = mockk<Source>()
        every { sourcesRepository.getCurrentSource() } returns mockedSource

        val result = sourcesRepository.getCurrentSource()

        assertEquals(mockedSource, result)
    }
}
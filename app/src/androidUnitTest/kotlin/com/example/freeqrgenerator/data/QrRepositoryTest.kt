package com.example.freeqrgenerator.data

import androidx.compose.ui.graphics.Color
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [33])
class QrRepositoryTest {

    private val repository = QrRepositoryImpl()

    @Test
    fun `Given valid url When generate is called Then returns success with non empty bytes`() = runTest {
        val result = repository.generate(
            url = "https://example.com",
            foregroundColor = Color.Black,
            backgroundColor = Color.White,
            cornersRadius = 0.2f,
            logoBytes = null
        )

        result.isSuccess shouldBe true
        result.getOrNull()?.isNotEmpty() shouldBe true
    }

    @Test
    fun `Given empty url When generate is called Then returns success`() = runTest {
        val result = repository.generate(
            url = "",
            foregroundColor = Color.Black,
            backgroundColor = Color.White,
            cornersRadius = 0.2f,
            logoBytes = null
        )

        result.isSuccess shouldBe true
    }

    @Test
    fun `Given corrupted logo bytes When generate is called Then returns failure`() = runTest {
        val result = repository.generate(
            url = "https://example.com",
            foregroundColor = Color.Black,
            backgroundColor = Color.White,
            cornersRadius = 0.2f,
            logoBytes = listOf(1, 2)
        )

        result.isFailure shouldBe true
    }
}
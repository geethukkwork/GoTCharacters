package com.example.gotcharacters.ui.list

import com.example.gotcharacters.domain.model.Character
import com.example.gotcharacters.domain.usecase.GetCharactersUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersListViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        getCharactersUseCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadsCharacters_andFiltersSearchQuery() = runTest {
        val characters = listOf(
            Character(
                localId = 1,
                stableKey = "character_1_Jon Snow_Kit Harington",
                fullName = "Jon Snow",
                culture = "Northmen",
                born = "In 283 AC",
                died = "—",
                title = "Lord Commander of the Night's Watch",
                actor = "Kit Harington",
                aliases = listOf("Lord Snow"),
                seasons = listOf(1, 2, 3, 4, 5, 6)
            ),
            Character(
                localId = 2,
                stableKey = "character_2_Daenerys Targaryen_Emilia Clarke",
                fullName = "Daenerys Targaryen",
                culture = "Valyrian",
                born = "In 284 AC, at Dragonstone",
                died = "—",
                title = "Queen of Meereen",
                actor = "Emilia Clarke",
                aliases = listOf("Dany"),
                seasons = listOf(1, 2, 3, 4, 5, 6)
            )
        )

        coEvery { getCharactersUseCase() } returns characters

        val viewModel = CharactersListViewModel(getCharactersUseCase)
        advanceUntilIdle()

        assertThat(viewModel.uiState.value.filteredCharacters).hasSize(2)

        viewModel.onQueryChanged("jon")
        assertThat(viewModel.uiState.value.filteredCharacters).hasSize(1)
        assertThat(viewModel.uiState.value.filteredCharacters.first().fullName).isEqualTo("Jon Snow")

        viewModel.onQueryChanged("valyrian")
        assertThat(viewModel.uiState.value.filteredCharacters).hasSize(1)
        assertThat(viewModel.uiState.value.filteredCharacters.first().fullName).isEqualTo("Daenerys Targaryen")
    }

    @Test
    fun emitsErrorState_whenUseCaseFails() = runTest {
        coEvery { getCharactersUseCase() } throws RuntimeException("Boom")

        val viewModel = CharactersListViewModel(getCharactersUseCase)
        advanceUntilIdle()

        assertThat(viewModel.uiState.value.isLoading).isFalse()
        assertThat(viewModel.uiState.value.errorMessage).isEqualTo("Something went wrong. Please try again.")
    }
}
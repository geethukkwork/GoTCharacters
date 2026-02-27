package com.example.gotcharacters.ui.list

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gotcharacters.domain.model.Character
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
class CharactersListScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun screen_shows_character_list() {
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
                aliases = emptyList(),
                seasons = listOf(1, 2, 3)
            ),
            Character(
                localId = 2,
                stableKey = "character_2_Arya Stark_Maisie Williams",
                fullName = "Arya Stark",
                culture = "Northmen",
                born = "In 289 AC, at Winterfell",
                died = "—",
                title = "Princess",
                actor = "Maisie Williams",
                aliases = emptyList(),
                seasons = listOf(1, 2, 3, 4)
            )
        )

        composeRule.setContent {
            CharactersListContent(
                uiState = CharactersListUiState(
                    characters = characters,
                    filteredCharacters = characters
                ),
                onQueryChanged = {},
                onRetry = {},
                onCharacterClick = {}
            )
        }

        composeRule.onNodeWithText("Jon Snow").assertExists()
        composeRule.onNodeWithText("Arya Stark").assertExists()
        composeRule.onNodeWithTag("search_field").assertExists()
    }

    @Test
    fun screen_shows_empty_state_when_no_results() {
        composeRule.setContent {
            CharactersListContent(
                uiState = CharactersListUiState(
                    query = "zzz",
                    characters = emptyList(),
                    filteredCharacters = emptyList()
                ),
                onQueryChanged = {},
                onRetry = {},
                onCharacterClick = {}
            )
        }

        composeRule.onNodeWithText("No characters found").assertExists()
        composeRule.onNodeWithTag("empty_state").assertExists()
    }

    @Test
    fun screen_shows_error_state() {
        composeRule.setContent {
            CharactersListContent(
                uiState = CharactersListUiState(
                    errorMessage = "Something went wrong"
                ),
                onQueryChanged = {},
                onRetry = {},
                onCharacterClick = {}
            )
        }

        composeRule.onNodeWithText("Something went wrong").assertExists()
        composeRule.onNodeWithTag("retry_button").assertExists()
    }

    @Test
    fun clicking_character_invokes_callback() {
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
                aliases = emptyList(),
                seasons = listOf(1, 2, 3)
            )
        )

        var clickedId: Int? = null

        composeRule.setContent {
            CharactersListContent(
                uiState = CharactersListUiState(
                    characters = characters,
                    filteredCharacters = characters
                ),
                onQueryChanged = {},
                onRetry = {},
                onCharacterClick = { clickedId = it }
            )
        }

        composeRule.onNodeWithText("Jon Snow").performClick()

        assert(clickedId == 1)
    }
}
package com.example.gotcharacters.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gotcharacters.ui.details.CharacterDetailsScreen
import com.example.gotcharacters.ui.details.CharacterDetailsViewModel
import com.example.gotcharacters.ui.list.CharactersListScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestinations.CharactersList.route,
        modifier = modifier
    ) {
        composable(AppDestinations.CharactersList.route) {
            CharactersListScreen(
                onCharacterClick = { id ->
                    navController.navigate(AppDestinations.CharacterDetails.createRoute(id))
                }
            )
        }

        composable(
            route = AppDestinations.CharacterDetails.route,
            arguments = listOf(
                navArgument("characterId") { type = NavType.IntType }
            )
        ) {
            val viewModel: CharacterDetailsViewModel = hiltViewModel()
            CharacterDetailsScreen(
                viewModel = viewModel,
                onBackClick = {
                    val didPop = navController.popBackStack()
                    if (!didPop) {
                        navController.navigate(AppDestinations.CharactersList.route) {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = false
                            }
                        }
                    }
                }            )
        }
    }
}
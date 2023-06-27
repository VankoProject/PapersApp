package com.kliachenko.papersapp.feature_paper.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kliachenko.papersapp.feature_paper.presentation.add_edit_paper.components.AddEditPaperScreen
import com.kliachenko.papersapp.feature_paper.presentation.papers.components.PaperScreen
import com.kliachenko.papersapp.feature_paper.presentation.util.Screen
import com.kliachenko.papersapp.ui.theme.PapersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PapersAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PapersScreen.route
                    ) {
                        composable(route = Screen.PapersScreen.route) {
                            PaperScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditPaperScreen.route +
                                    "?paperId={paperId}&paperColor={paperColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "paperId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "paperColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },

                                )
                        ) {
                            val color = it.arguments?.getInt("paperColor") ?: -1
                            AddEditPaperScreen(
                                navController = navController,
                                paperColor = color
                            )
                        }
                    }
                }
            }
        }
    }
}



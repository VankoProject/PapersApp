package com.kliachenko.papersapp.feature_paper.presentation.util

sealed class Screen (val route: String) {
    object PapersScreen: Screen("papers_screen")
    object AddEditPaperScreen: Screen("add_edit_paper_screen")
}

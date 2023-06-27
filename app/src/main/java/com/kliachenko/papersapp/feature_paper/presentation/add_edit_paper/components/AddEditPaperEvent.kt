package com.kliachenko.papersapp.feature_paper.presentation.add_edit_paper.components

import androidx.compose.ui.focus.FocusState

sealed class AddEditPaperEvent {
    data class EnteredTitle(val value: String): AddEditPaperEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditPaperEvent()
    data class EnteredContent(val value: String): AddEditPaperEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditPaperEvent()
    data class ChangeColor(val color: Int): AddEditPaperEvent()
    object SavePaper: AddEditPaperEvent()
}



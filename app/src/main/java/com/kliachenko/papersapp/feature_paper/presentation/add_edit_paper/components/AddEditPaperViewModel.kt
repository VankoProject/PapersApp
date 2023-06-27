package com.kliachenko.papersapp.feature_paper.presentation.add_edit_paper.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kliachenko.papersapp.feature_paper.domain.model.InvalidPaperException
import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import com.kliachenko.papersapp.feature_paper.domain.use_case.PaperUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditPaperViewModel @Inject constructor(
    private val paperUseCases: PaperUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _paperTitle = mutableStateOf(PaperTextFieldState(
        hint = "Enter title..."
    ))
    val paperTitle: State<PaperTextFieldState> = _paperTitle

    private val _paperContent = mutableStateOf(PaperTextFieldState(
        hint = "Enter some content"
    ))
    val paperContent: State<PaperTextFieldState> = _paperContent

    private val _paperColor = mutableStateOf(Paper.paperColors.random().toArgb())
    val paperColor: State<Int> = _paperColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentPaperId: Int? = null

    init {
        savedStateHandle.get<Int>("paperId") ?.let { paperId ->
            if (paperId != -1) {
                viewModelScope.launch {
                    paperUseCases.getPaper(paperId)?.also { paper ->
                        currentPaperId = paper.id
                        _paperTitle.value = paperTitle.value.copy(
                            text = paper.title,
                            isHintVisible = false
                        )
                        _paperContent.value = _paperContent.value.copy(
                            text = paper.content,
                            isHintVisible = false
                        )
                        _paperColor.value = paper.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditPaperEvent) {
        when(event) {
            is AddEditPaperEvent.EnteredTitle -> {
                _paperTitle.value = paperTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditPaperEvent.ChangeTitleFocus -> {
                _paperTitle.value = paperTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            paperTitle.value.text.isBlank()
                )
            }
            is AddEditPaperEvent.EnteredContent -> {
                _paperContent.value = _paperContent.value.copy(
                    text = event.value
                )
            }
            is AddEditPaperEvent.ChangeContentFocus -> {
                _paperContent.value = _paperContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _paperContent.value.text.isBlank()
                )
            }
            is AddEditPaperEvent.ChangeColor -> {
                _paperColor.value = event.color
            }
            is AddEditPaperEvent.SavePaper -> {
                viewModelScope.launch {
                    try {
                        paperUseCases.addPaper(
                            Paper(
                                title = paperTitle.value.text,
                                content = paperContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = paperColor.value,
                                id = currentPaperId
                            )
                        )
                        _eventFlow.emit(UiEvent.SavePaper)
                    } catch (e: InvalidPaperException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save paper"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar (val message: String): UiEvent()
        object SavePaper: UiEvent()
    }
}
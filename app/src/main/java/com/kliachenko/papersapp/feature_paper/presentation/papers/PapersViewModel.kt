package com.kliachenko.papersapp.feature_paper.presentation.papers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import com.kliachenko.papersapp.feature_paper.domain.use_case.PaperUseCases
import com.kliachenko.papersapp.feature_paper.domain.util.OrderType
import com.kliachenko.papersapp.feature_paper.domain.util.PaperOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PapersViewModel @Inject constructor(
    private val paperUseCases: PaperUseCases
) : ViewModel() {

    private val _state = mutableStateOf(PaperState())
    val state: State<PaperState> = _state

    private var recentlyDeletePaper: Paper? = null

    private var getPaperJob: Job? = null

    init {
        getPapers(PaperOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: PapersEvent) {
        when (event) {
            is PapersEvent.Order -> {
                if (state.value.paperOrder::class == event.paperOrder::class &&
                    state.value.paperOrder.orderType == event.paperOrder.orderType
                ) {
                    return
                }
                getPapers(event.paperOrder)
            }
            is PapersEvent.DeletePaper -> {
                viewModelScope.launch {
                    paperUseCases.deletePaper(event.paper)
                    recentlyDeletePaper = event.paper
                }
            }
            is PapersEvent.RestorePaper -> {
                viewModelScope.launch {
                    paperUseCases.addPaper(recentlyDeletePaper ?: return@launch)
                    recentlyDeletePaper = null
                }
            }
            is PapersEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getPapers(paperOrder: PaperOrder) {
        getPaperJob?.cancel()
        getPaperJob = paperUseCases.getPapers(paperOrder)
            .onEach { papers ->
                _state.value = state.value.copy(
                    papers = papers,
                    paperOrder = paperOrder
                )
            }
            .launchIn(viewModelScope)
    }

}
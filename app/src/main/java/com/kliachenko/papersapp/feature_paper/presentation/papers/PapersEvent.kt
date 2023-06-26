package com.kliachenko.papersapp.feature_paper.presentation.papers

import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import com.kliachenko.papersapp.feature_paper.domain.util.PaperOrder

sealed class PapersEvent {

    data class Order(val paperOrder: PaperOrder): PapersEvent()
    data class DeletePaper(val paper: Paper): PapersEvent()
    object RestorePaper: PapersEvent()
    object ToggleOrderSection: PapersEvent()
}
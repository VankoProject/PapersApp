package com.kliachenko.papersapp.feature_paper.presentation.papers

import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import com.kliachenko.papersapp.feature_paper.domain.util.OrderType
import com.kliachenko.papersapp.feature_paper.domain.util.PaperOrder

data class PaperState(

    val papers: List<Paper> = emptyList(),
    val paperOrder: PaperOrder = PaperOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)

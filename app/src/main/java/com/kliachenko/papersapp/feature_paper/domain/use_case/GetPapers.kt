package com.kliachenko.papersapp.feature_paper.domain.use_case

import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import com.kliachenko.papersapp.feature_paper.domain.repository.PaperRepository
import com.kliachenko.papersapp.feature_paper.domain.util.OrderType
import com.kliachenko.papersapp.feature_paper.domain.util.PaperOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPapers(
    private val repository: PaperRepository
) {
    operator fun invoke(
        paperOrder: PaperOrder = PaperOrder.Date(OrderType.Descending)
    ): Flow<List<Paper>> {
        return repository.getPapers().map { papers ->
            when (paperOrder.orderType) {
                is OrderType.Ascending -> {
                    when (paperOrder) {
                        is PaperOrder.Title -> papers.sortedBy { it.title.lowercase() }
                        is PaperOrder.Date -> papers.sortedBy { it.timestamp }
                        is PaperOrder.Color -> papers.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when (paperOrder) {
                        is PaperOrder.Title -> papers.sortedByDescending { it.title.lowercase() }
                        is PaperOrder.Date -> papers.sortedByDescending { it.timestamp }
                        is PaperOrder.Color -> papers.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}
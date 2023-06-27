package com.kliachenko.papersapp.feature_paper.domain.use_case

import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import com.kliachenko.papersapp.feature_paper.domain.repository.PaperRepository

class GetPaper(
    private val repository: PaperRepository
) {

    suspend operator fun invoke (id: Int): Paper? {
        return repository.getPaperById(id)
    }
}
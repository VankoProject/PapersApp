package com.kliachenko.papersapp.feature_paper.domain.use_case

import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import com.kliachenko.papersapp.feature_paper.domain.repository.PaperRepository

class DeletePaper(
    private val repository: PaperRepository
) {

    suspend operator fun invoke(paper: Paper) {
        repository.deletePaper(paper)
    }
}
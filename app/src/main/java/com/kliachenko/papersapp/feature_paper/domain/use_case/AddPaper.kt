package com.kliachenko.papersapp.feature_paper.domain.use_case

import com.kliachenko.papersapp.feature_paper.domain.model.InvalidPaperException
import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import com.kliachenko.papersapp.feature_paper.domain.repository.PaperRepository

class AddPaper(
    private val repository: PaperRepository
) {
    @Throws(InvalidPaperException::class)
    suspend operator fun invoke(paper: Paper) {
        if (paper.title.isBlank()) {

            throw InvalidPaperException("The title of the paper can't be empty")
        }
        if (paper.content.isBlank()) {
            throw InvalidPaperException("The content of the paper can't be empty")
        }
        repository.insertPaper(paper)
    }
}
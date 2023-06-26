package com.kliachenko.papersapp.feature_paper.domain.repository

import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import kotlinx.coroutines.flow.Flow

interface PaperRepository {

    fun getPapers(): Flow<List<Paper>>

    suspend fun getPaperById(id: Int): Paper?

    suspend fun insertPaper(paper: Paper)

    suspend fun deletePaper(paper: Paper)
}
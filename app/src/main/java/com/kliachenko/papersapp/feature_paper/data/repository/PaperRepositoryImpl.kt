package com.kliachenko.papersapp.feature_paper.data.repository

import com.kliachenko.papersapp.feature_paper.data.data_source.PaperDao
import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import com.kliachenko.papersapp.feature_paper.domain.repository.PaperRepository
import kotlinx.coroutines.flow.Flow

class PaperRepositoryImpl(
    private val dao: PaperDao
): PaperRepository{
    override fun getPapers(): Flow<List<Paper>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPaperById(id: Int): Paper? {
        TODO("Not yet implemented")
    }

    override suspend fun insertPaper(paper: Paper) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePaper(paper: Paper) {
        TODO("Not yet implemented")
    }
}
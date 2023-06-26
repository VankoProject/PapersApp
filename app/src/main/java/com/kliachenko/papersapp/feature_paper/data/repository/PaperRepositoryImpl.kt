package com.kliachenko.papersapp.feature_paper.data.repository

import com.kliachenko.papersapp.feature_paper.data.data_source.PaperDao
import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import com.kliachenko.papersapp.feature_paper.domain.repository.PaperRepository
import kotlinx.coroutines.flow.Flow

class PaperRepositoryImpl(
    private val dao: PaperDao
): PaperRepository{
    override fun getPapers(): Flow<List<Paper>> {
        return dao.getNotes()
    }

    override suspend fun getPaperById(id: Int): Paper? {
        return dao.getPaperById(id)
    }

    override suspend fun insertPaper(paper: Paper) {
        dao.insertPaper(paper)
    }

    override suspend fun deletePaper(paper: Paper) {
        dao.deletePaper(paper)
    }
}
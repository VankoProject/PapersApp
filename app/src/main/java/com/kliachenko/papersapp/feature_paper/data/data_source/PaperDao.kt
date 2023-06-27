package com.kliachenko.papersapp.feature_paper.data.data_source

import androidx.room.*
import com.kliachenko.papersapp.feature_paper.domain.model.Paper
import kotlinx.coroutines.flow.Flow

@Dao
interface PaperDao {

    @Query("SELECT * FROM paper")
    fun getNotes(): Flow<List<Paper>>

    @Query("SELECT * FROM paper WHERE id =:id")
    suspend fun getPaperById(id: Int): Paper?

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaper(paper: Paper)

    @Delete
    suspend fun deletePaper(paper: Paper)
}
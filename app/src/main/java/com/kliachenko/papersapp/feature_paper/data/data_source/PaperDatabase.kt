package com.kliachenko.papersapp.feature_paper.data.data_source

import androidx.compose.runtime.Composable
import androidx.room.Database
import androidx.room.RoomDatabase
import com.kliachenko.papersapp.feature_paper.domain.model.Paper

@Database(
    entities = [Paper::class],
    version = 1
)
abstract class PaperDatabase: RoomDatabase() {

    abstract val paperDao: PaperDao

    companion object {
        const val DATABASE_NAME = "paper_db"
    }
}
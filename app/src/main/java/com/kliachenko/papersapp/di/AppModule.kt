package com.kliachenko.papersapp.di

import android.app.Application
import androidx.room.Room
import com.kliachenko.papersapp.feature_paper.data.data_source.PaperDatabase
import com.kliachenko.papersapp.feature_paper.data.repository.PaperRepositoryImpl
import com.kliachenko.papersapp.feature_paper.domain.repository.PaperRepository
import com.kliachenko.papersapp.feature_paper.domain.use_case.DeletePaper
import com.kliachenko.papersapp.feature_paper.domain.use_case.GetPapers
import com.kliachenko.papersapp.feature_paper.domain.use_case.PaperUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaperDatabase(app: Application): PaperDatabase {
        return Room.databaseBuilder(
            app,
            PaperDatabase::class.java,
            PaperDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePaperRepository(db: PaperDatabase): PaperRepository {
        return PaperRepositoryImpl(db.paperDao)
    }

    @Provides
    @Singleton
    fun providePaperUseCases(repository: PaperRepository): PaperUseCases {
        return PaperUseCases(
            getPapers = GetPapers(repository),
            deletePaper = DeletePaper(repository)
        )
    }
}
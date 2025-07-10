package com.example.funnycombination.di

import android.content.Context
import com.example.funnycombination.data.dao.HighScoreDao
import com.example.funnycombination.data.database.AppDatabase
import com.example.funnycombination.data.repository.HighScoreRepository
import com.example.funnycombination.domain.usecase.CheckSequenceUseCase
import com.example.funnycombination.domain.usecase.GenerateSequenceUseCase
import com.example.funnycombination.domain.usecase.SaveHighScoreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideHighScoreDao(database: AppDatabase): HighScoreDao {
        return database.highScoreDao()
    }

    @Provides
    @Singleton
    fun provideHighScoreRepository(highScoreDao: HighScoreDao): HighScoreRepository {
        return HighScoreRepository(highScoreDao)
    }

    @Provides
    @Singleton
    fun provideGenerateSequenceUseCase(): GenerateSequenceUseCase {
        return GenerateSequenceUseCase()
    }

    @Provides
    @Singleton
    fun provideCheckSequenceUseCase(): CheckSequenceUseCase {
        return CheckSequenceUseCase()
    }

    @Provides
    @Singleton
    fun provideSaveHighScoreUseCase(repository: HighScoreRepository): SaveHighScoreUseCase {
        return SaveHighScoreUseCase(repository)
    }
}
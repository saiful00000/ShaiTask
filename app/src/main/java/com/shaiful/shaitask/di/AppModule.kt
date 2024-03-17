package com.shaiful.shaitask.di

import android.app.Application
import androidx.room.Room
import com.shaiful.shaitask.modules.notes.data.data_source.NoteDatabase
import com.shaiful.shaitask.modules.notes.data.repository.NoteRepositoryImpl
import com.shaiful.shaitask.modules.notes.domain.repository.NoteRepository
import com.shaiful.shaitask.modules.notes.domain.use_case.DeleteNoteUseCase
import com.shaiful.shaitask.modules.notes.domain.use_case.GetNotesUseCase
import com.shaiful.shaitask.modules.notes.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(dao = noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(noteRepository),
            deleteNoteUseCase = DeleteNoteUseCase(noteRepository),
        )
    }

}
package com.shaiful.shaitask.modules.notes.domain.use_case

import com.shaiful.shaitask.modules.notes.domain.model.InvalidNoteException
import com.shaiful.shaitask.modules.notes.domain.model.Note
import com.shaiful.shaitask.modules.notes.domain.repository.NoteRepository

class AddNoteUseCase(
    private val noteRepository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) throw InvalidNoteException("Notes title can't be empty.")

        if (note.content.isBlank()) throw InvalidNoteException("Notes content can't be empty")

        noteRepository.insertNote(note)
    }
}
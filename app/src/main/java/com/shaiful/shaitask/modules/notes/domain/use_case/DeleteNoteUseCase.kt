package com.shaiful.shaitask.modules.notes.domain.use_case

import com.shaiful.shaitask.modules.notes.domain.model.Note
import com.shaiful.shaitask.modules.notes.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note = note)
    }
}
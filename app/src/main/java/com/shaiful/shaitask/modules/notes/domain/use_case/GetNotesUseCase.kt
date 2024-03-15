package com.shaiful.shaitask.modules.notes.domain.use_case

import com.shaiful.shaitask.modules.notes.domain.model.Note
import com.shaiful.shaitask.modules.notes.domain.repository.NoteRepository
import com.shaiful.shaitask.modules.notes.util.NoteOrderBy
import com.shaiful.shaitask.modules.notes.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(
        noteOrderBy: NoteOrderBy = NoteOrderBy.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return noteRepository.getAllNotes().map { notes ->
            when (noteOrderBy.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrderBy) {
                        is NoteOrderBy.Color -> notes.sortedBy { it.color }
                        is NoteOrderBy.Date -> notes.sortedBy { it.timeStamp }
                        is NoteOrderBy.Title -> notes.sortedBy { it.title.lowercase() }
                    }
                }

                is OrderType.Descending -> {
                    when (noteOrderBy) {
                        is NoteOrderBy.Color -> notes.sortedByDescending { it.color }
                        is NoteOrderBy.Date -> notes.sortedByDescending { it.timeStamp }
                        is NoteOrderBy.Title -> notes.sortedByDescending { it.title.lowercase() }
                    }
                }
            }
        }
    }
}
package com.shaiful.shaitask.modules.notes.presentation.events_n_states

import com.shaiful.shaitask.modules.notes.domain.model.Note
import com.shaiful.shaitask.modules.notes.util.NoteOrderBy

sealed class NotesEvent {
    data class OrderEvent(val noteOrderBy: NoteOrderBy): NotesEvent()
    data class DeleteEvent(val note: Note): NotesEvent()
    data class AddEvent(val note: Note): NotesEvent()
    data object RestoreEvent: NotesEvent()
    data object OrderSectionHideView: NotesEvent()
}
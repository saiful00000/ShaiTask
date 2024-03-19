package com.shaiful.shaitask.modules.notes.presentation.events_n_states

import com.shaiful.shaitask.modules.notes.domain.model.Note
import com.shaiful.shaitask.modules.notes.util.NoteOrderBy
import com.shaiful.shaitask.modules.notes.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrderBy: NoteOrderBy = NoteOrderBy.Date(orderType = OrderType.Descending),
    val orderSectionVisible: Boolean = false
)
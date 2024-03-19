package com.shaiful.shaitask.modules.notes.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaiful.shaitask.modules.notes.domain.model.Note
import com.shaiful.shaitask.modules.notes.domain.use_case.NoteUseCases
import com.shaiful.shaitask.modules.notes.presentation.events_n_states.NotesEvent
import com.shaiful.shaitask.modules.notes.presentation.events_n_states.NotesState
import com.shaiful.shaitask.modules.notes.util.NoteOrderBy
import com.shaiful.shaitask.modules.notes.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _notesState = mutableStateOf(NotesState())
    val notesState: State<NotesState> = _notesState
    private var getNotesJob: Job? = null

    private var lastlyDeletedNote: Note? = null

    init {
        _getNotes(NoteOrderBy.Date(OrderType.Descending))
    }

    fun onNoteEvent(notesEvent: NotesEvent) {
        when (notesEvent) {
            is NotesEvent.AddEvent -> {
                viewModelScope.launch {

                }
            }

            is NotesEvent.DeleteEvent -> {
                viewModelScope.launch {
                    lastlyDeletedNote = notesEvent.note
                    noteUseCases.deleteNoteUseCase(notesEvent.note)
                }
            }

            is NotesEvent.RestoreEvent -> {
                viewModelScope.launch {
                    noteUseCases.addNoteUseCase(lastlyDeletedNote ?: return@launch)
                    lastlyDeletedNote = null
                }
            }
            is NotesEvent.OrderEvent -> {
                val isSameClass = notesState.value.noteOrderBy::class == notesEvent.noteOrderBy::class
                val isSameOrderType = notesState.value.noteOrderBy.orderType == notesEvent.noteOrderBy.orderType

                if(isSameClass && isSameOrderType) {
                    return
                }

                _getNotes(notesEvent.noteOrderBy)
            }
            is NotesEvent.OrderSectionHideView -> {
                _notesState.value = _notesState.value.copy(
                    orderSectionVisible = !_notesState.value.orderSectionVisible
                )
            }
        }
    }

    private fun _getNotes(noteOrderBy: NoteOrderBy) {
        getNotesJob?.cancel()

        getNotesJob = noteUseCases.getNotesUseCase(noteOrderBy = noteOrderBy)
            .onEach {notes ->
                _notesState.value = _notesState.value.copy(
                    notes = notes,
                    noteOrderBy = noteOrderBy
                )
            }.launchIn(viewModelScope)
    }

}
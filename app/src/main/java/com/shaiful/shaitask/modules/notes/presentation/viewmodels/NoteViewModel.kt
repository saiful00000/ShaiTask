package com.shaiful.shaitask.modules.notes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shaiful.shaitask.modules.notes.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel()  {

}
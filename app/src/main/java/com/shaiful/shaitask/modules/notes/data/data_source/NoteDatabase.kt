package com.shaiful.shaitask.modules.notes.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaiful.shaitask.modules.notes.domain.model.Note

@Database(
    version = 1,
    entities = [Note::class]
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao
}
package com.example.noteit.notedetails

import android.app.Application
import androidx.lifecycle.*
import com.example.noteit.data.Note
import com.example.noteit.data.source.local.NoteDatabase
import com.example.noteit.data.source.local.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)
        }
    }}
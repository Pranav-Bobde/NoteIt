package com.example.noteit.notes

import android.app.Application
import androidx.lifecycle.*
import com.example.noteit.data.Note
import com.example.noteit.data.source.local.NoteDatabase
import com.example.noteit.data.source.local.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application){

    val allNotes: LiveData<List<Note>>
    private val repository: NoteRepository

    private val _navigateToSelectedNote = MutableLiveData<Note>()
    val navigateToSelectedNote: LiveData<Note>
        get() = _navigateToSelectedNote

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}
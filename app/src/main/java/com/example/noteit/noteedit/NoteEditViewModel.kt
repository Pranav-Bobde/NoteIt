package com.example.noteit.noteedit

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteit.data.Note
import com.example.noteit.data.source.local.NoteDatabase
import com.example.noteit.data.source.local.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteEditViewModel(application: Application): AndroidViewModel(application) {
    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
    }

    fun onSave(view: View, note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(note)
        }
    }}
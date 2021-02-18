package com.example.noteit.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteit.data.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)
    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from note_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

}
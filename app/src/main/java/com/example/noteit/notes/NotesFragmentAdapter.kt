package com.example.noteit.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.noteit.R
import com.example.noteit.data.Note

class NotesFragmentAdapter(private val context: Context, private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesFragmentAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById<TextView>(R.id.titleTextItem)
        val note: TextView = itemView.findViewById<TextView>(R.id.noteTextItem)
        val time: TextView = itemView.findViewById<TextView>(R.id.timeTextView)
        val image: ImageView = itemView.findViewById<ImageView>(R.id.imageViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.itemView.setOnClickListener { listener.onItemClicked(currentNote) }
        holder.title.text = currentNote.title
        holder.note.text = currentNote.text
        holder.time.text = currentNote.time
        Glide.with(context)
            .load(currentNote.image)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}

interface INotesRVAdapter {
    fun onItemClicked(note: Note)
}
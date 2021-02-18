package com.example.noteit.notedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.noteit.R
import kotlinx.android.synthetic.main.note_details_fragment.*

class NoteDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = NoteDetailsFragment()
    }

    private lateinit var viewModel: NoteDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
        .get(NoteDetailsViewModel::class.java)

        val note = NoteDetailsFragmentArgs.fromBundle(arguments!!).note
        titleTextItemDetail.text = note.title
        noteTextItemDetail.text = note.text
        timeTextViewDetail.text = note.time
        Glide.with(this)
            .load(note.image)
            .into(imageViewItemDetail)

        deleteButton.setOnClickListener {
            viewModel.deleteNote(note)
            it.findNavController().navigate(NoteDetailsFragmentDirections.actionNoteDetailsFragmentToNotesFragment())
        }
    }

}
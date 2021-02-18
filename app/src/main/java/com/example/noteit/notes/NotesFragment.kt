package com.example.noteit.notes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteit.R
import com.example.noteit.data.Note
import kotlinx.android.synthetic.main.notes_fragment.*

class NotesFragment : Fragment(), INotesRVAdapter {

    companion object {
        fun newInstance() = NotesFragment()
    }

    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(NotesViewModel::class.java)
        // TODO: Use the ViewModel
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NotesFragmentAdapter(requireContext(), this)
        recyclerView.adapter = adapter

        viewModel.allNotes.observe(this, Observer {list ->
            list?.let{
                adapter.updateList(it)
            }
        })

        floatingActionButton.setOnClickListener {
            view?.findNavController()?.navigate(NotesFragmentDirections.actionNotesFragmentToNoteEditFragment())
        }

    }

    override fun onItemClicked(note: Note) {
        view?.findNavController()?.navigate(NotesFragmentDirections.actionNotesFragmentToNoteDetailsFragment(note))
    }

}
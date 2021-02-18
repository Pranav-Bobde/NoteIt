package com.example.noteit.noteedit

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.noteit.DateUtils
import com.example.noteit.R
import com.example.noteit.data.Note
import kotlinx.android.synthetic.main.note_edit_fragment.*
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class NoteEditFragment : Fragment() {
    private lateinit var image: ByteArray
    private lateinit var time: String
    companion object {
        fun newInstance() = NoteEditFragment()
    }

    private lateinit var viewModel: NoteEditViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_edit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(NoteEditViewModel::class.java)

        //On Tapping Camera
        cameraButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }
        //On Tapping Gallery
        gallerybutton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 456)
        }

        //On Tapping Save
        saveButton.setOnClickListener {
            val noteText = addNoteEditView.text.toString().trim()
            val noteTitle = titleEditView.text.toString().trim()
            time = DateUtils.formatDateTimeFromDate(DateUtils.DATE_FORMAT_9, Calendar.getInstance().time)!!
            if(noteText.isNotEmpty()){
                viewModel.onSave(it, Note(noteText, noteTitle, time, image))
                view!!.findNavController().navigate(NoteEditFragmentDirections.actionNoteEditFragmentToNotesFragment())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
        val bytes = ByteArrayOutputStream()
        val bmp: Bitmap
        if(requestCode == 123) {
            bmp = data?.extras?.get("data") as Bitmap
            bmp.compress(Bitmap.CompressFormat.JPEG, 0, bytes)
            imageView.setImageBitmap(bmp)
        }else if(requestCode == 456) {
            imageView.setImageURI(data?.data)
        }
        image = bytes.toByteArray()

    }

}
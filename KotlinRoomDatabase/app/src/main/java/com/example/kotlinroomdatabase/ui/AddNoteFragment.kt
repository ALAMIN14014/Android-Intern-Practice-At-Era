package com.example.kotlinroomdatabase.ui

import android.app.ActionBar
import android.app.Activity
import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.kotlinroomdatabase.R
import com.example.kotlinroomdatabase.db.Note
import com.example.kotlinroomdatabase.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch


class AddNoteFragment : BaseFragment() {

    var note: Note? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            note = AddNoteFragmentArgs.fromBundle(it).note
            edit_text_title.setText(note?.title)
            edit_text_note.setText(note?.note)
        }

        button_save.setOnClickListener {

            val noteTile = edit_text_title.text.toString().trim()
            val noteBody = edit_text_note.text.toString().trim()

            if (noteTile.isEmpty()) {
                edit_text_title.error = "Title reqired"
                edit_text_title.requestFocus()
                return@setOnClickListener
            }

            if (noteBody.isEmpty()) {
                edit_text_note.error = "Note required"
                edit_text_note.requestFocus()
                return@setOnClickListener
            }

            launch {

                context?.let {
                    val mnote = Note(noteTile, noteBody)
                    if (note == null) {

                        NoteDatabase(it).getNotdao().addNote(mnote)
                        it.toast("Note Saved")

                    } else {
                        mnote.id = note!!.id
                        NoteDatabase(it).getNotdao().updateNote(mnote)
                        it.toast("Note Updated")


                    }
                    val action = AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment()
                    view?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
                }

            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> if (note != null) deleteNote() else context?.toast("Can not delete")

        }
        return super.onOptionsItemSelected(item)

    }

    private fun deleteNote() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You can not unde this operation")
            setPositiveButton("Yes") { _, _ ->
                launch {
                    NoteDatabase(context).getNotdao().deleteNote(note!!)
                    val action = AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment()
                    view?.let { it1 -> Navigation.findNavController(it1).navigate(action) }

                }
            }
            setNegativeButton("No") { _, _ ->

            }
        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }


}


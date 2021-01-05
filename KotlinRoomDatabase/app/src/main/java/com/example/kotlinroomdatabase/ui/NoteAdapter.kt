package com.example.kotlinroomdatabase.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinroomdatabase.R
import com.example.kotlinroomdatabase.db.Note

class NoteAdapter(val notes: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false)
        )
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.title.text = notes[position].title
        holder.noteBody.text = notes[position].note
        holder.view.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
            action.note=notes[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class NoteViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var title: TextView = view.findViewById(R.id.titleTextViewId)
        var noteBody: TextView = view.findViewById(R.id.noteBodyTextViewId)

    }
}
package me.diegoramos.ceep.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.list_notes_activity.*
import me.diegoramos.ceep.R
import me.diegoramos.ceep.dao.NotesDAO
import me.diegoramos.ceep.model.Note
import me.diegoramos.ceep.ui.adapter.ListNotesAdapter

class ListNotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_notes_activity)

        val allNotes: List<Note> = fetchNotes()
        configureRecyclerView(allNotes)
    }

    private fun fetchNotes(): List<Note> =
        NotesDAO().all

    override fun onResume() {
        val allNotes: List<Note> = fetchNotes()
        configureRecyclerView(allNotes)
        super.onResume()
    }

    private fun configureRecyclerView(allNotes: List<Note>) {
        list_notes.adapter = ListNotesAdapter(allNotes)
    }

    fun goToFormNote(view: View) {
        val intent = Intent(applicationContext, FormNoteActivity::class.java)
        startActivity(intent)
    }

}

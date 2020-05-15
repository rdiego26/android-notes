package me.diegoramos.ceep.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.diegoramos.ceep.R
import me.diegoramos.ceep.dao.NotesDAO
import me.diegoramos.ceep.model.Note
import me.diegoramos.ceep.ui.adapter.ListNotesAdapter

class ListNotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_notes_activity)

        val notesList: RecyclerView = findViewById(R.id.list_notes)
        val notesDao = NotesDAO()

        for(i in 0..10000) {
            notesDao.add(Note("Title $i", "Description $i"))
        }

        val allNotes: List<Note> = notesDao.all

        notesList.adapter = ListNotesAdapter(allNotes)
        val linearLayoutManager = LinearLayoutManager(this)
        notesList.layoutManager = linearLayoutManager
    }
}

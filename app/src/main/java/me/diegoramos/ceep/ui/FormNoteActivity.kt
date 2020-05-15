package me.diegoramos.ceep.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.form_note_activity.*
import me.diegoramos.ceep.R
import me.diegoramos.ceep.dao.NotesDAO
import me.diegoramos.ceep.model.Note

class FormNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.form_note_activity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.form_note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.form_note_menu_add) {
            val note = Note(form_note_title.text.toString(), form_note_description.text.toString())
            NotesDAO().add(note)
        }
        return super.onOptionsItemSelected(item)
    }

}
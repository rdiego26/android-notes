package me.diegoramos.ceep.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.form_note_activity.*
import me.diegoramos.ceep.R
import me.diegoramos.ceep.dao.NotesDAO
import me.diegoramos.ceep.model.Note
import me.diegoramos.ceep.util.Constants

class FormNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_note_activity)

        handleReceivedData()
    }

    private fun handleReceivedData() {
        val receivedData = intent
        if (receivedData.hasExtra(Constants.NOTE_EXTRA_NAME)) {
            val receivedNote: Note =
                receivedData.getSerializableExtra(Constants.NOTE_EXTRA_NAME) as Note
            form_note_title.setText(receivedNote.title)
            form_note_description.setText(receivedNote.description)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.form_note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.form_note_menu_add) {
            val createdNote = Note(form_note_title.text.toString(), form_note_description.text.toString())
            NotesDAO().add(createdNote)
            setResult(Constants.CREATED_NOTE_RESULT_CODE, prepareResult(createdNote))
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun prepareResult(note: Note): Intent {
        val intent = Intent()
        intent.putExtra(Constants.CREATED_NOTE_EXTRA_NAME, note)

        return intent
    }

}
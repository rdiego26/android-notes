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

    companion object {
        const val INVALID_POSITION: Int = -1
    }

    private var receivedNote: Note? = null
    private var receivedNotePosition: Int = INVALID_POSITION
    private var isEditMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_note_activity)

        handleFormMode()
        handleReceivedData()
    }

    private fun handleFormMode() {
        isEditMode = hasReceivedNote(intent)
    }

    private fun handleReceivedData() {
        if (isEditMode) {
            val receivedData = intent
            receivedNote =
                receivedData.getSerializableExtra(Constants.NOTE_EXTRA_NAME) as Note
            receivedNotePosition = receivedData.getIntExtra(Constants.NOTE_POSITION_EXTRA_NAME,
                INVALID_POSITION)
            form_note_title.setText(receivedNote?.title)
            form_note_description.setText(receivedNote?.description)
        }
    }

    private fun hasReceivedNote(intent: Intent) =
        intent.hasExtra(Constants.NOTE_EXTRA_NAME)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.form_note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.form_note_menu_save) {
            val createdNote = Note(form_note_title.text.toString(), form_note_description.text.toString())

            if (isEditMode) {
                NotesDAO().update(receivedNotePosition, receivedNote)
            } else {
                NotesDAO().add(createdNote)
            }

            setResult(Constants.SAVED_NOTE_RESULT_CODE, prepareResult(createdNote))
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun prepareResult(note: Note): Intent {
        val intent = Intent()
        intent.putExtra(Constants.CREATED_NOTE_EXTRA_NAME, note)
        intent.putExtra(Constants.NOTE_POSITION_EXTRA_NAME, receivedNotePosition)

        return intent
    }

}
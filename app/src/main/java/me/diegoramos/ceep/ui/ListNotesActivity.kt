package me.diegoramos.ceep.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.list_notes_activity.*
import me.diegoramos.ceep.R
import me.diegoramos.ceep.dao.NotesDAO
import me.diegoramos.ceep.model.Note
import me.diegoramos.ceep.ui.adapter.ListNotesAdapter
import me.diegoramos.ceep.ui.adapter.listener.OnItemClickListener
import me.diegoramos.ceep.util.Constants

class ListNotesActivity : AppCompatActivity(), OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_notes_activity)

        configureRecyclerView()
        configureLinkToForm()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (isCreateNoteRequest(requestCode) && isSaveNoteResult(resultCode) && hasNote(data)) {
            addItemOnAdapter(data!!)
        } else if(isUpdateNoteRequest(requestCode) && isSaveNoteResult(resultCode) && hasNote(data)
            && hasNotePosition(data)) {
            updateItemOnAdapter(data!!)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun hasNote(data: Intent?) =
        data?.hasExtra(Constants.CREATED_NOTE_EXTRA_NAME)!!

    private fun hasNotePosition(data: Intent?) =
        data?.hasExtra(Constants.NOTE_POSITION_EXTRA_NAME)!!

    private fun isSaveNoteResult(resultCode: Int) =
        resultCode == Constants.SAVED_NOTE_RESULT_CODE

    private fun isCreateNoteRequest(requestCode: Int) =
        requestCode == Constants.CREATE_NOTE_REQUEST_CODE

    private fun isUpdateNoteRequest(requestCode: Int) =
        requestCode == Constants.UPDATE_NOTE_REQUEST_CODE

    private fun addItemOnAdapter(data: Intent) {
        val receivedNote: Note = data.getSerializableExtra(Constants.CREATED_NOTE_EXTRA_NAME) as Note
        ((this.list_notes.adapter) as ListNotesAdapter).addNote(receivedNote)
//        this.list_notes.adapter?.notifyDataSetChanged()
    }

    private fun updateItemOnAdapter(data: Intent) {
        val receivedNote: Note = data.getSerializableExtra(Constants.CREATED_NOTE_EXTRA_NAME) as Note
        val receivedNotePosition: Int = data.getIntExtra(Constants.NOTE_POSITION_EXTRA_NAME, -1)
        ((this.list_notes.adapter) as ListNotesAdapter).updateNote(receivedNote, receivedNotePosition)

    }

    private fun configureRecyclerView() {
        this.list_notes.adapter = ListNotesAdapter(NotesDAO().all, this)
    }

    private fun configureLinkToForm() {
        list_notes_activity_footer_text.setOnClickListener {
            val intent = Intent(applicationContext, FormNoteActivity::class.java)
            startActivityForResult(intent, Constants.CREATE_NOTE_REQUEST_CODE)
        }
    }

    override fun onItemClick(note: Note, position: Int) {
        val intent = Intent(applicationContext, FormNoteActivity::class.java)
        intent.putExtra(Constants.NOTE_EXTRA_NAME, note)
        intent.putExtra(Constants.NOTE_POSITION_EXTRA_NAME, position)
        startActivityForResult(intent, Constants.UPDATE_NOTE_REQUEST_CODE)
    }

}

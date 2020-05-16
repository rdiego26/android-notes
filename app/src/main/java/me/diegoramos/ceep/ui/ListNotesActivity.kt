package me.diegoramos.ceep.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.list_notes_activity.*
import me.diegoramos.ceep.R
import me.diegoramos.ceep.dao.NotesDAO
import me.diegoramos.ceep.model.Note
import me.diegoramos.ceep.ui.adapter.ListNotesAdapter
import me.diegoramos.ceep.util.Constants

class ListNotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_notes_activity)

        configureRecyclerView()
        configureLinkToForm()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constants.createdNoteRequestCode()
            && resultCode == Constants.createdNoteResultCode()
            && data?.hasExtra(Constants.createdNoteExtraName())!!) {
            addItemOnAdapter(data)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun addItemOnAdapter(data: Intent) {
        val receivedNote: Note = data.getSerializableExtra(Constants.createdNoteExtraName()) as Note
        ((this.list_notes.adapter) as ListNotesAdapter).addNote(receivedNote)
        this.list_notes.adapter?.notifyDataSetChanged()
    }

    private fun configureRecyclerView() {
        this.list_notes.adapter = ListNotesAdapter(NotesDAO().all)
    }

    private fun configureLinkToForm() {
        list_notes_activity_footer_text.setOnClickListener {
            val intent = Intent(applicationContext, FormNoteActivity::class.java)
            startActivityForResult(intent, Constants.createdNoteRequestCode())
        }
    }

}

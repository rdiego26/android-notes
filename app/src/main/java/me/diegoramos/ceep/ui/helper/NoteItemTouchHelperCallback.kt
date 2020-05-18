package me.diegoramos.ceep.ui.helper

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import me.diegoramos.ceep.R
import me.diegoramos.ceep.dao.NotesDAO
import me.diegoramos.ceep.ui.adapter.ListNotesAdapter

class NoteItemTouchHelperCallback(private val listNotesAdapter: ListNotesAdapter,
                                  private val context: Context)
    : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags: Int = ItemTouchHelper.RIGHT.or(ItemTouchHelper.LEFT)
            .or(ItemTouchHelper.UP).or(ItemTouchHelper.DOWN)
        val swipeFlags: Int = ItemTouchHelper.RIGHT.or(ItemTouchHelper.LEFT)
         return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val initialPosition = viewHolder.adapterPosition
        val newPosition = target.adapterPosition
        changeNotesPosition(initialPosition, newPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        NotesDAO().remove(viewHolder.adapterPosition)
        listNotesAdapter.removeNote(viewHolder.adapterPosition)

        Toast.makeText(context,
            R.string.remove_note_feedback,
            Toast.LENGTH_SHORT
        ).show()

    }

    private fun changeNotesPosition(initialPosition: Int, newPosition: Int) {
        NotesDAO().changePosition(initialPosition, newPosition)
        listNotesAdapter.changePosition(initialPosition, newPosition)
    }

}

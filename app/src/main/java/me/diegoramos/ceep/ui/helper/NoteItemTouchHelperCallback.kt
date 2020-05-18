package me.diegoramos.ceep.ui.helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import me.diegoramos.ceep.dao.NotesDAO
import me.diegoramos.ceep.ui.adapter.ListNotesAdapter

class NoteItemTouchHelperCallback(private val listNotesAdapter: ListNotesAdapter) : ItemTouchHelper.Callback() {

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
        NotesDAO().changePosition(initialPosition, newPosition)
        listNotesAdapter.changePosition(initialPosition, newPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        NotesDAO().remove(viewHolder.adapterPosition)
        listNotesAdapter.removeNote(viewHolder.adapterPosition)
    }

}

package me.diegoramos.ceep.ui.helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import me.diegoramos.ceep.dao.NotesDAO
import me.diegoramos.ceep.ui.adapter.ListNotesAdapter

class NoteItemTouchHelperCallback(private val listNotesAdapter: ListNotesAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int =
        makeMovementFlags(0, ItemTouchHelper.RIGHT.or(ItemTouchHelper.LEFT))

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        NotesDAO().remove(viewHolder.adapterPosition)
        listNotesAdapter.removeNote(viewHolder.adapterPosition)
    }

}

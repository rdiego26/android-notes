package me.diegoramos.ceep.ui.adapter;


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import me.diegoramos.ceep.R
import me.diegoramos.ceep.model.Note

class ListNotesAdapter(private val list: MutableList<Note>,
                       private val click: (item: Note) -> Unit) : RecyclerView.Adapter<ListNoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListNoteViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListNoteViewHolder, position: Int) {
        holder.bind(list[position], click)
    }

    fun addNote(note: Note) {
        list.add(note)
        notifyDataSetChanged()
    }


}

class ListNoteViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.note_item, parent, false)) {

    fun bind(note: Note, click: (item: Note) -> Unit) {
        itemView.note_item_title.text = note.title
        itemView.note_item_description.text = note.description

        itemView.setOnClickListener { click(note) }
    }
}

package me.diegoramos.ceep.ui.adapter.listener

import me.diegoramos.ceep.model.Note

interface OnItemClickListener {

    fun onItemClick(note: Note, position: Int)
}
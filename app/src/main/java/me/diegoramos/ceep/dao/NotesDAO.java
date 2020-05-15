package me.diegoramos.ceep.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.diegoramos.ceep.model.Note;

public class NotesDAO {

    private final static ArrayList<Note> Notes = new ArrayList<>();

    public List<Note> getAll() {
        return (List<Note>) Notes.clone();
    }

    public void add(Note... Notes) {
        NotesDAO.Notes.addAll(Arrays.asList(Notes));
    }

    public void update(int position, Note Note) {
        Notes.set(position, Note);
    }

    public void remove(int position) {
        Notes.remove(position);
    }

    public void changePosition(int initialPosition, int finalPosition) {
        Collections.swap(Notes, initialPosition, finalPosition);
    }

    public void removeAll() {
        Notes.clear();
    }
}

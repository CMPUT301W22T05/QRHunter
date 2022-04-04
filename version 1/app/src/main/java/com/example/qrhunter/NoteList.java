package com.example.qrhunter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteList {

    private List<Note> notes = new ArrayList<>();

    /**
     * This adds a note to the list if the note does not exist
     *
     * @throws IllegalArgumentException
     *      If the note already exist in the list this is thrown
     * @param note
     * This is a candidate note to add
     */

    public void add(Note note) {
        if (notes.contains(note)) {
            throw new IllegalArgumentException();
        }
        notes.add(note);
    }


    /**
     * This returns a sorted list of notes
     * @return list
     * Return the sorted list
     */
    public List<Note> getNotes() {
        List<Note> list = notes;
        Collections.sort(list);
        return list;
    }

    /**
     * This shows if the note exists in the list or not
     * @return bool
     * @param note
     * Return boolean
     */

    public boolean noteExist(Note note){
        if(notes.contains(note)){
            return true;
        }
        return false;
    }

    /**
     * This delete a note in the list if the note exists
     *
     * @throws IllegalArgumentException
     *      If the note does not exist in the list
     * @param note
     * This is a note to delete
     */

    public void deleteNote(Note note) {
        if (notes.contains(note)) {
            notes.remove(note);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This returns the number of notes in the list
     * @return notes.size
     * This is an integer represents the size
     */
    public int countNote(){
        return notes.size();
    }
}

package com.example.qrhunter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NoteListTest {


    private NoteList mockNoteList() {
        NoteList noteList = new NoteList();
        noteList.add(mockNote());
        return noteList;
    }
    private Note mockNote() {
        return new Note("note1", "good",37,53.526,-113.527,"http://www.google.com");
    }

    /**
     * This tests the functionality of adding notes
     */
    @Test
    void testAdd() {
        NoteList noteList = mockNoteList();
        assertEquals(1, noteList.getNotes().size());

        Note note = new Note("note2", "not good",52,23.526,113.527,"http://www.ualberta.com");
        noteList.add(note);

        assertEquals(2, noteList.getNotes().size());
        assertTrue(noteList.getNotes().contains(note));
    }

    /**
     * This tests the exception of adding
     */
    @Test
    void testAddException() {
        NoteList noteList = mockNoteList();
        Note note = new Note("note3", "bad",10,76.516,113.527,"http://www.baidu.com");
        noteList.add(note);
        assertThrows(IllegalArgumentException.class, () -> {
            noteList.add(note);
        });
    }

    /**
     * This tests the functionality of getting notes from the list
     */

    @Test
    void testGetNotes() {
        NoteList noteList = mockNoteList();
        assertEquals(0, mockNote().compareTo(noteList.getNotes().get(0)));
        Note note = new Note("note4", "perfect",100,80.533,120.527,"http://www.beartrack.com");
        noteList.add(note);
        assertEquals(9, note.compareTo(noteList.getNotes().get(0)));
    }

    /**
     * This tests if the note exist in the list or not
     */

    @Test
    void testExistence(){
        NoteList noteList = mockNoteList();
        Note note = new Note("note5", "bad",2,20.516,114.527,"http://www.gmail.com");
        assertFalse(noteList.noteExist(note));
    }

    /**
     * This tests the functionality of counting notes from the list
     */
    @Test
    void testCount(){
        NoteList noteList = mockNoteList();
        assertEquals(noteList.getNotes().size(),noteList.countNote());
    }

    /**
     * This tests the functionality of deleting notes from the list
     */

    @Test
    void testDelete(){
        NoteList noteList = mockNoteList();
        Note note = new Note("note6", "so so",41,41.216,45.527,"http://www.android.com");
        noteList.add(note);
        assertEquals(2,noteList.countNote());
        noteList.deleteNote(note);
        assertEquals(1,noteList.countNote());
        assertFalse(noteList.getNotes().contains(note));
    }

    /**
     * This tests the exception of deleting notes from the list
     */
    @Test
    void testDeleteException() {
        NoteList noteList = mockNoteList();
        Note note = new Note("note7", "boring",30,76.016,2.527,"http://www.lenovo.com");
        assertThrows(IllegalArgumentException.class, () -> {
            noteList.deleteNote(note);
        });
    }
}

package com.example.qrhunter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ScoreOnOwnerPersonalPageListTest {


    private ScoreOnOwnerPersonalPageList mockScoreOnOwnerPersonalPageList() {
        ScoreOnOwnerPersonalPageList scoreOnOwnerPersonalPageList = new ScoreOnOwnerPersonalPageList();
        scoreOnOwnerPersonalPageList.add(mockScoreOnOwnerPersonalPage());
        return scoreOnOwnerPersonalPageList;
    }
    private ScoreOnOwnerPersonalPage mockScoreOnOwnerPersonalPage() {
        return new ScoreOnOwnerPersonalPage("note1", "150","abc");
    }

    /**
     * This tests the functionality of adding scoreOnOwnerPersonalPages
     */
    @Test
    void testAdd() {
        ScoreOnOwnerPersonalPageList scoreOnOwnerPersonalPageList = mockScoreOnOwnerPersonalPageList();
        assertEquals(1, scoreOnOwnerPersonalPageList.getScoreOnOwnerPersonalPages().size());

        ScoreOnOwnerPersonalPage scoreOnOwnerPersonalPage = new ScoreOnOwnerPersonalPage("note2", "200","def");
        scoreOnOwnerPersonalPageList.add(scoreOnOwnerPersonalPage);

        assertEquals(2, scoreOnOwnerPersonalPageList.getScoreOnOwnerPersonalPages().size());
        assertTrue(scoreOnOwnerPersonalPageList.getScoreOnOwnerPersonalPages().contains(scoreOnOwnerPersonalPage));
    }

    /**
     * This tests the exception of adding
     */
    @Test
    void testAddException() {
        ScoreOnOwnerPersonalPageList scoreOnOwnerPersonalPageList = mockScoreOnOwnerPersonalPageList();
        ScoreOnOwnerPersonalPage scoreOnOwnerPersonalPage = new ScoreOnOwnerPersonalPage("note3", "250","ghi");
        scoreOnOwnerPersonalPageList.add(scoreOnOwnerPersonalPage);
        assertThrows(IllegalArgumentException.class, () -> {
            scoreOnOwnerPersonalPageList.add(scoreOnOwnerPersonalPage);
        });
    }

    /**
     * This tests the functionality of getting scoreOnOwnerPersonalPages from the list
     */

    @Test
    void testGetScoreOnOwnerPersonalPages() {
        ScoreOnOwnerPersonalPageList scoreOnOwnerPersonalPageList = mockScoreOnOwnerPersonalPageList();
        assertEquals(0, mockScoreOnOwnerPersonalPage().compareTo(scoreOnOwnerPersonalPageList.getScoreOnOwnerPersonalPages().get(0)));
        ScoreOnOwnerPersonalPage scoreOnOwnerPersonalPage = new ScoreOnOwnerPersonalPage("note4", "50","jkl");
        scoreOnOwnerPersonalPageList.add(scoreOnOwnerPersonalPage);
        assertEquals(3, scoreOnOwnerPersonalPage.compareTo(scoreOnOwnerPersonalPageList.getScoreOnOwnerPersonalPages().get(0)));
    }

    /**
     * This tests if the scoreOnOwnerPersonalPage exist in the list or not
     */

    @Test
    void testExistence(){
        ScoreOnOwnerPersonalPageList scoreOnOwnerPersonalPageList = mockScoreOnOwnerPersonalPageList();
        ScoreOnOwnerPersonalPage scoreOnOwnerPersonalPage = new ScoreOnOwnerPersonalPage("note5", "15","mno");
        assertFalse(scoreOnOwnerPersonalPageList.scoreOnOwnerPersonalPageExist(scoreOnOwnerPersonalPage));
    }

    /**
     * This tests the functionality of counting scoreOnOwnerPersonalPages from the list
     */
    @Test
    void testCount(){
        ScoreOnOwnerPersonalPageList scoreOnOwnerPersonalPageList = mockScoreOnOwnerPersonalPageList();
        assertEquals(scoreOnOwnerPersonalPageList.getScoreOnOwnerPersonalPages().size(),scoreOnOwnerPersonalPageList.countScoreOnOwnerPersonalPage());
    }

    /**
     * This tests the functionality of deleting scoreOnOwnerPersonalPages from the list
     */

    @Test
    void testDelete(){
        ScoreOnOwnerPersonalPageList scoreOnOwnerPersonalPageList = mockScoreOnOwnerPersonalPageList();
        ScoreOnOwnerPersonalPage scoreOnOwnerPersonalPage = new ScoreOnOwnerPersonalPage("note6", "453","pqr");
        scoreOnOwnerPersonalPageList.add(scoreOnOwnerPersonalPage);
        assertEquals(2,scoreOnOwnerPersonalPageList.countScoreOnOwnerPersonalPage());
        scoreOnOwnerPersonalPageList.deleteScoreOnOwnerPersonalPage(scoreOnOwnerPersonalPage);
        assertEquals(1,scoreOnOwnerPersonalPageList.countScoreOnOwnerPersonalPage());
        assertFalse(scoreOnOwnerPersonalPageList.getScoreOnOwnerPersonalPages().contains(scoreOnOwnerPersonalPage));
    }

    /**
     * This tests the exception of deleting notes from the list
     */
    @Test
    void testDeleteException() {
        ScoreOnOwnerPersonalPageList scoreOnOwnerPersonalPageList = mockScoreOnOwnerPersonalPageList();
        ScoreOnOwnerPersonalPage scoreOnOwnerPersonalPage = new ScoreOnOwnerPersonalPage("note7", "38","stuvwxyz");
        assertThrows(IllegalArgumentException.class, () -> {
            scoreOnOwnerPersonalPageList.deleteScoreOnOwnerPersonalPage(scoreOnOwnerPersonalPage);
        });
    }
}

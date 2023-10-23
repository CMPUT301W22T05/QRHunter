package com.example.qrhunter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TotalScoreOnOwnerPageListTest {

    private TotalScoreOnOwnerPageList mockTotalScoreOnOwnerPageList() {
        TotalScoreOnOwnerPageList totalScoreOnOwnerPageList = new TotalScoreOnOwnerPageList();
        totalScoreOnOwnerPageList.add(mockTotalScoreOnOwnerPage());
        return totalScoreOnOwnerPageList;
    }
    private TotalScoreOnOwnerPage mockTotalScoreOnOwnerPage() {
        return new TotalScoreOnOwnerPage("player1", "30");
    }

    /**
     * This tests the functionality of adding players
     */
    @Test
    void testAdd() {
        TotalScoreOnOwnerPageList totalScoreOnOwnerPageList = mockTotalScoreOnOwnerPageList();
        assertEquals(1, totalScoreOnOwnerPageList.getPlayers().size());

        TotalScoreOnOwnerPage totalScoreOnOwnerPage = new TotalScoreOnOwnerPage("player2", "58");
        totalScoreOnOwnerPageList.add(totalScoreOnOwnerPage);

        assertEquals(2, totalScoreOnOwnerPageList.getPlayers().size());
        assertTrue(totalScoreOnOwnerPageList.getPlayers().contains(totalScoreOnOwnerPage));
    }

    /**
     * This tests the exception of adding
     */
    @Test
    void testAddException() {
        TotalScoreOnOwnerPageList totalScoreOnOwnerPageList = mockTotalScoreOnOwnerPageList();
        TotalScoreOnOwnerPage totalScoreOnOwnerPage = new TotalScoreOnOwnerPage("player3", "80");
        totalScoreOnOwnerPageList.add(totalScoreOnOwnerPage);
        assertThrows(IllegalArgumentException.class, () -> {
            totalScoreOnOwnerPageList.add(totalScoreOnOwnerPage);
        });
    }

    /**
     * This tests if the player exist in the list or not
     */
    @Test
    void testExistence(){
        TotalScoreOnOwnerPageList totalScoreOnOwnerPageList = mockTotalScoreOnOwnerPageList();
        TotalScoreOnOwnerPage totalScoreOnOwnerPage = new TotalScoreOnOwnerPage("player5", "54");
        assertFalse(totalScoreOnOwnerPageList.playerExist(totalScoreOnOwnerPage));
    }

    /**
     * This tests the functionality of counting players from the list
     */
    @Test
    void testCount(){
        TotalScoreOnOwnerPageList totalScoreOnOwnerPageList = mockTotalScoreOnOwnerPageList();
        assertEquals(totalScoreOnOwnerPageList.getPlayers().size(),totalScoreOnOwnerPageList.countPlayer());
    }

    /**
     * This tests the functionality of deleting players from the list
     */
    @Test
    void testDelete(){
        TotalScoreOnOwnerPageList totalScoreOnOwnerPageList = mockTotalScoreOnOwnerPageList();
        TotalScoreOnOwnerPage totalScoreOnOwnerPage = new TotalScoreOnOwnerPage("player6", "100");
        totalScoreOnOwnerPageList.add(totalScoreOnOwnerPage);
        assertEquals(2,totalScoreOnOwnerPageList.getPlayers().size());
        totalScoreOnOwnerPageList.deletePlayer(totalScoreOnOwnerPage);
        assertEquals(1,totalScoreOnOwnerPageList.getPlayers().size());
        assertFalse(totalScoreOnOwnerPageList.getPlayers().contains(totalScoreOnOwnerPageList));
    }

    /**
     * This tests the exception of deleting players from the list
     */
    @Test
    void testDeleteException() {
        TotalScoreOnOwnerPageList totalScoreOnOwnerPageList = mockTotalScoreOnOwnerPageList();
        TotalScoreOnOwnerPage totalScoreOnOwnerPage = new TotalScoreOnOwnerPage("player7", "75");
        assertThrows(IllegalArgumentException.class, () -> {
            totalScoreOnOwnerPageList.deletePlayer(totalScoreOnOwnerPage);
        });
    }

}

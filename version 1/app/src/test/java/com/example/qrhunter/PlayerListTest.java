package com.example.qrhunter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PlayerListTest {

    private PlayerList mockPlayerList() {
        PlayerList playerList = new PlayerList();
        playerList.add(mockPlayer());
        return playerList;
    }
    private Player mockPlayer() {
        return new Player("player1", "30");
    }

    @Test
    void testAdd() {
        PlayerList playerList = mockPlayerList();
        assertEquals(1, playerList.getPlayers().size());

        Player player = new Player("player2", "58");
        playerList.add(player);

        assertEquals(2, playerList.getPlayers().size());
        assertTrue(playerList.getPlayers().contains(player));
    }

    @Test
    void testAddException() {
        PlayerList playerList = mockPlayerList();
        Player player = new Player("player3", "80");
        playerList.add(player);
        assertThrows(IllegalArgumentException.class, () -> {
            playerList.add(player);
        });
    }

    @Test
    void testGetPlayers() {
        PlayerList playerList = mockPlayerList();
        assertEquals(0, mockPlayer().compareTo(playerList.getPlayers().get(0)));
        Player player = new Player("player4", "153");
        playerList.add(player);
        assertEquals(3, player.compareTo(playerList.getPlayers().get(0)));
    }

    @Test
    void testExistence(){
        PlayerList playerList = mockPlayerList();
        Player player = new Player("player5", "54");
        assertFalse(playerList.playerExist(player));
    }

    @Test
    void testCount(){
        PlayerList playerList = mockPlayerList();
        assertEquals(playerList.getPlayers().size(),playerList.countPlayer());
    }


    @Test
    void testDelete(){
        PlayerList playerList = mockPlayerList();
        Player player = new Player("player6", "100");
        playerList.add(player);
        assertEquals(2,playerList.getPlayers().size());
        playerList.deletePlayer(player);
        assertEquals(1,playerList.getPlayers().size());
        assertFalse(playerList.getPlayers().contains(player));
    }

    @Test
    void testDeleteException() {
        PlayerList playerList = mockPlayerList();
        Player player = new Player("player7", "75");
        assertThrows(IllegalArgumentException.class, () -> {
            playerList.deletePlayer(player);
        });
    }

}

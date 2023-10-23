package com.example.qrhunter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a class that keeps track of a list of Player objects
 */
public class PlayerList {

    private List<Player> players = new ArrayList<>();

    /**
     * This adds a player to the list if the player does not exist
     *
     * @throws IllegalArgumentException
     *      If the player already exist in the list this is thrown
     * @param player
     * This is a candidate player to add
     */

    public void add(Player player) {
        if (players.contains(player)) {
            throw new IllegalArgumentException();
        }
        players.add(player);
    }


    /**
     * This returns a sorted list of players
     * @return list
     * Return the sorted list
     */
    public List<Player> getPlayers() {
        List<Player> list = players;
        Collections.sort(list);
        return list;
    }

    /**
     * This shows if the player exists in the list or not
     * @return bool
     * @param player
     * Return boolean
     */

    public boolean playerExist(Player player){
        if(players.contains(player)){
            return true;
        }
        return false;
    }

    /**
     * This delete a player in the list if the player exists
     *
     * @throws IllegalArgumentException
     *      If the player does not exist in the list
     * @param player
     * This is a player to delete
     */

    public void deletePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This returns the number of players in the list
     * @return players.size
     * This is an integer represents the size
     */
    public int countPlayer(){
        return players.size();
    }
}

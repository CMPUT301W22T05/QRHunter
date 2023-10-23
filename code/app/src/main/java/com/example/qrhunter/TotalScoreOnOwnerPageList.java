package com.example.qrhunter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TotalScoreOnOwnerPageList {
    private List<TotalScoreOnOwnerPage> players = new ArrayList<>();

    /**
     * This adds a player to the list if the player does not exist
     *
     * @throws IllegalArgumentException
     *      If the player already exist in the list this is thrown
     * @param totalscoreonownerpage
     * This is a candidate player to add
     */

    public void add(TotalScoreOnOwnerPage totalscoreonownerpage) {
        if (players.contains(totalscoreonownerpage)) {
            throw new IllegalArgumentException();
        }
        players.add(totalscoreonownerpage);
    }


    /**
     * This returns a sorted list of players
     * @return list
     * Return the sorted list
     */
    public List<TotalScoreOnOwnerPage> getPlayers() {
        List<TotalScoreOnOwnerPage> list = players;
        Collections.sort(list);
        return list;
    }

    /**
     * This shows if the player exists in the list or not
     * @return bool
     * @param totalscoreonownerpage
     * Return boolean
     */

    public boolean playerExist(TotalScoreOnOwnerPage totalscoreonownerpage){
        if(players.contains(totalscoreonownerpage)){
            return true;
        }
        return false;
    }

    /**
     * This delete a player in the list if the player exists
     *
     * @throws IllegalArgumentException
     *      If the player does not exist in the list
     * @param totalscoreonownerpage
     * This is a player to delete
     */

    public void deletePlayer(TotalScoreOnOwnerPage totalscoreonownerpage) {
        if (players.contains(totalscoreonownerpage)) {
            players.remove(totalscoreonownerpage);
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


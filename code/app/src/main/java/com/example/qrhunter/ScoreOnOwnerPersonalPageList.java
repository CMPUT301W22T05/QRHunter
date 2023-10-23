package com.example.qrhunter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreOnOwnerPersonalPageList {

    private List<ScoreOnOwnerPersonalPage> scoreOnOwnerPersonalPages = new ArrayList<>();

    /**
     * This adds a scoreOnOwnerPersonalPage to the list if the scoreOnOwnerPersonalPage does not exist
     *
     * @throws IllegalArgumentException
     *      If the scoreOnOwnerPersonalPage already exist in the list this is thrown
     * @param scoreOnOwnerPersonalPage
     * This is a candidate scoreOnOwnerPersonalPage to add
     */

    public void add(ScoreOnOwnerPersonalPage scoreOnOwnerPersonalPage) {
        if (scoreOnOwnerPersonalPages.contains(scoreOnOwnerPersonalPage)) {
            throw new IllegalArgumentException();
        }
        scoreOnOwnerPersonalPages.add(scoreOnOwnerPersonalPage);
    }


    /**
     * This returns a sorted list of scoreOnOwnerPersonalPages
     * @return list
     * Return the sorted list
     */
    public List<ScoreOnOwnerPersonalPage> getScoreOnOwnerPersonalPages() {
        List<ScoreOnOwnerPersonalPage> list = scoreOnOwnerPersonalPages;
        Collections.sort(list);
        return list;
    }

    /**
     * This shows if the scoreOnOwnerPersonalPage exists in the list or not
     * @return bool
     * @param scoreOnOwnerPersonalPage
     * Return boolean
     */

    public boolean scoreOnOwnerPersonalPageExist(ScoreOnOwnerPersonalPage scoreOnOwnerPersonalPage){
        if(scoreOnOwnerPersonalPages.contains(scoreOnOwnerPersonalPage)){
            return true;
        }
        return false;
    }

    /**
     * This delete a scoreOnOwnerPersonalPage in the list if the scoreOnOwnerPersonalPage exists
     *
     * @throws IllegalArgumentException
     *      If the scoreOnOwnerPersonalPage does not exist in the list
     * @param scoreOnOwnerPersonalPage
     * This is a scoreOnOwnerPersonalPage to delete
     */

    public void deleteScoreOnOwnerPersonalPage(ScoreOnOwnerPersonalPage scoreOnOwnerPersonalPage) {
        if (scoreOnOwnerPersonalPages.contains(scoreOnOwnerPersonalPage)) {
            scoreOnOwnerPersonalPages.remove(scoreOnOwnerPersonalPage);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This returns the number of scoreOnOwnerPersonalPages in the list
     * @return scoreOnOwnerPersonalPages.size
     * This is an integer represents the size
     */
    public int countScoreOnOwnerPersonalPage(){
        return scoreOnOwnerPersonalPages.size();
    }
}

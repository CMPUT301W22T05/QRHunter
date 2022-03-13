package com.example.qrhunter;

import android.app.Activity;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class OwnerMenuActivityTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<OwnerMenuActivity> rule =
            new ActivityTestRule<>(OwnerMenuActivity.class, true, true);


    /**
     * Runs before all tests and creates solo instance.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());
    }
    /**
     * Gets the Activity
     * @throws Exception
     */
    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }

    /**
     * Test the search button
     */
    @Test
    public void checkSearchButton(){
        solo.assertCurrentActivity("Wrong Activity", OwnerMenuActivity.class);

    }

    /**
     * Test the ranking button
     */
    @Test
    public void checkRankingButton(){
        solo.assertCurrentActivity("Wrong Activity", OwnerMenuActivity.class);
        solo.clickOnButton("RANKING");
        solo.assertCurrentActivity("Wrong Activity", RankingActivity.class);
    }

}

package com.example.qrhunter;

import android.app.Activity;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class SearchTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<Search> rule =
            new ActivityTestRule<>(Search.class, true, true);


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
     * Test the scan button
     */
    @Test
    public void checkScanButton(){
        solo.assertCurrentActivity("Wrong Activity", PlayerMenuActivity.class);

        solo.clickOnButton("ic_menu_camera");
        //solo.assertCurrentActivity("Wrong Activity", RankingActivity.class);
    }

    /**
     * Test the view my QR code button
     */
    @Test
    public void checkUsername(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.enterText((EditText) solo.getView(R.id.username_input), "player1");
        solo.enterText((EditText) solo.getView(R.id.password_input), "321");
        solo.clickOnButton("Log in");
        solo.assertCurrentActivity("Test failed",PlayerMenuActivity.class);

    }

    /**
     * Test the search button
     */
    @Test
    public void checkSearchButton(){
        solo.assertCurrentActivity("Wrong Activity", PlayerMenuActivity.class);
        solo.clickOnButton("Search");
        //solo.assertCurrentActivity("Wrong Activity", RankingActivity.class);
    }

    /**
     * Test the ranking button
     */
    @Test
    public void checkRankingButton(){
        solo.assertCurrentActivity("Wrong Activity", PlayerMenuActivity.class);
        solo.clickOnButton("RANKING");
        //solo.assertCurrentActivity("Wrong Activity", RankingActivity.class);
    }

}

package com.example.qrhunter;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

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
     * Test the search username
     */
    @Test
    public void checkSearchButton(){
        solo.assertCurrentActivity("Wrong Activity", Search.class);
        solo.enterText((EditText) solo.getView(R.id.Username),"test1");
        View searchButton = solo.getView("search");
        solo.clickOnView(searchButton);
        solo.assertCurrentActivity("Wrong Activity", SearchInfo.class);
    }

    /**
     * Test the scan button
     */
    @Test
    public void checkScanButton(){
        solo.assertCurrentActivity("Wrong Activity", Search.class);
        View scanButton = solo.getView("scan_btn");
        solo.clickOnView(scanButton);
        solo.assertCurrentActivity("Wrong Activity", SearchInfo.class);
    }


}

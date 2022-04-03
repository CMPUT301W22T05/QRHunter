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
        solo.clickOnImageButton(0); // not sure which button is
        //View searchButton = solo.getView("search");   //try
        //solo.clickOnView(searchButton);   //try
        solo.assertCurrentActivity("Wrong Activity", SearchInfo.class);
    }

    /**
     * Test the scan button
     */
    @Test
    public void checkScanButton(){
        solo.assertCurrentActivity("Wrong Activity", Search.class);
        solo.clickOnImageButton(1); // not sure which button is
        //View scanButton = solo.getView("scan_btn");   //try
        //solo.clickOnView(scanButton);   //try
        solo.assertCurrentActivity("Wrong Activity", SearchInfo.class);
    }


}

package com.example.qrhunter;

import android.app.Activity;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PlayerMenuActivityTest {
    // tests not passed!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private Solo solo;
    @Rule
    public ActivityTestRule<PlayerMenuActivity> rule =
            new ActivityTestRule<>(PlayerMenuActivity.class, true, true);


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
     * Test the scan QR code button
     */
    @Test
    public void checkScanButton(){
        solo.assertCurrentActivity("Wrong Activity", PlayerMenuActivity.class);
        solo.clickOnButton("Scan QR Code");
        solo.assertCurrentActivity("Wrong Activity", ScanQRcodeActivity.class);
    }

    /**
     * Test the view my QR code button
     */
    @Test
    public void checkViewButton(){
        solo.assertCurrentActivity("Wrong Activity", PlayerMenuActivity.class);
        solo.clickOnButton("View My QR Codes");
        solo.assertCurrentActivity("Wrong Activity", ViewQRCode.class);
    }


    /**
     * Test the search button
     */
    @Test
    public void checkSearchButton(){
        solo.assertCurrentActivity("Wrong Activity", PlayerMenuActivity.class);
        solo.clickOnButton("Search");
        solo.assertCurrentActivity("Wrong Activity", Search.class);
    }

    /**
     * Test the QR INFO button
     */
    @Test
    public void checkQRInfoButton(){
        solo.assertCurrentActivity("Wrong Activity", PlayerMenuActivity.class);
        solo.clickOnButton("QR INFO");
        solo.assertCurrentActivity("Wrong Activity", QrInfo.class);
    }

    /**
     * Test the ranking button
     */
    @Test
    public void checkRankingButton(){
        solo.assertCurrentActivity("Wrong Activity", PlayerMenuActivity.class);
        solo.clickOnButton("RANKING");
        solo.assertCurrentActivity("Wrong Activity", PlayerRankingActivity.class);
    }

    /**
     * Test the MyProfile button
     */
    @Test
    public void checkMyProfileButton(){
        solo.assertCurrentActivity("Wrong Activity", PlayerMenuActivity.class);
        solo.clickOnImageButton(0);
        solo.assertCurrentActivity("Wrong Activity", MyProfileActivity.class);
    }

    /**
     * Test the logout button
     */
    @Test
    public void checkLogoutButton(){
        solo.assertCurrentActivity("Wrong Activity", PlayerMenuActivity.class);
        solo.clickOnButton("LogOut");
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
    }

}

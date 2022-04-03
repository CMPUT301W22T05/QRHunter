package com.example.qrhunter;

import android.app.Activity;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ScanQRcodeActivityTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<ScanQRcodeActivity> rule =
            new ActivityTestRule<>(ScanQRcodeActivity.class,true,true);


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
        solo.assertCurrentActivity("Wrong Activity", ScanQRcodeActivity.class);
        solo.clickOnButton("Scan");
        solo.assertCurrentActivity("Wrong Activity", scannerView.class);
    }

    /**
     * Test the Enable location button
     */
    @Test
    public void checkEnableLocationButton(){
        //solo.assertCurrentActivity("Wrong Activity", ScanQRcodeActivity.class);
        //solo.clickOnButton("Enable Location");
        //solo.assertCurrentActivity("Wrong Activity", scannerView.class);
    }

    /**
     * Test of uploading comment
     */
    @Test
    public void checkCommentUpload(){
        //solo.assertCurrentActivity("Wrong Activity", ScanQRcodeActivity.class);
        //solo.enterText((EditText) solo.getView(R.id.editText_comments), "good");
        //solo.clickOnButton("Upload");
        //solo.assertCurrentActivity("Wrong Activity", scannerView.class);
    }

}

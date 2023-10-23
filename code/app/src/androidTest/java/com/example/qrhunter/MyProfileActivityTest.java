package com.example.qrhunter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MyProfileActivityTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<MyProfileActivity> rule =
            new ActivityTestRule<>(MyProfileActivity.class, true, true);


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
     * Test the generate button
     */
    @Test
    public void checkGenerateButton(){
        solo.assertCurrentActivity("Wrong Activity", MyProfileActivity.class);
        solo.clickOnButton("Generate");
        solo.assertCurrentActivity("Test failed",MyProfileActivity.class);
    }

    /**
     * Test the Edit profile button
     */
    @Test
    public void checkEditButton(){
        solo.assertCurrentActivity("Wrong Activity", MyProfileActivity.class);
        solo.clickOnButton("Edit profile");
        solo.enterText((EditText) solo.getView(R.id.username_editText), "test name");
        solo.enterText((EditText) solo.getView(R.id.contact_information_editText), "test2@gmail.com");
        solo.clickOnButton("Confirm");
        assertTrue(solo.searchText("Full name: test name"));
        assertTrue(solo.searchText("Contact Information: test2@gmail.com"));
    }
    
}

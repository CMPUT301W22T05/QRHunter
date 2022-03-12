package com.example.qrhunter;

import android.app.Activity;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test class for MainActivity. All the UI tests are written here. Robotium test framework is used
 */
public class MainActivityTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);


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
     * Test the signup button
     */
    @Test
    public void checkSignupButton(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("Sign up");
        solo.assertCurrentActivity("Test failed",SignUpActivity.class);
    }

    /**
     *  Test the player account login
     */
    @Test
    public void checkPlayerLogin(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.enterText((EditText) solo.getView(R.id.username_input), "player1");
        solo.enterText((EditText) solo.getView(R.id.password_input), "321");
        solo.clickOnButton("Log in");
        solo.assertCurrentActivity("Test failed",PlayerMenuActivity.class);

    }

    /**
     *  Test the owner account login
     */
    @Test
    public void checkOwnerLogin(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.enterText((EditText) solo.getView(R.id.username_input), "xuantong");
        solo.enterText((EditText) solo.getView(R.id.password_input), "123");
        solo.clickOnButton("Log in");
        solo.assertCurrentActivity("Test failed",OwnerMenuActivity.class);

    }

    /**
     * Check if an empty username can login or not
     *
     */
    @Test
    public void checkUsernameLogin(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.enterText((EditText) solo.getView(R.id.password_input), "emptyUsername");
        solo.clickOnButton("Log in");
        solo.assertCurrentActivity("Test failed",MainActivity.class);

    }

    /**
     * Check if an empty password can login or not
     */
    @Test
    public void checkPasswordLogin(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.enterText((EditText) solo.getView(R.id.username_input), "emptyPassword");
        solo.clickOnButton("Log in");
        solo.assertCurrentActivity("Test failed",MainActivity.class);

    }

}



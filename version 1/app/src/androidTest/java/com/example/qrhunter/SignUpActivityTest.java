package com.example.qrhunter;

import static org.hamcrest.JMock1Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
public class SignUpActivityTest {
    private Solo solo;
    @Rule
    public ActivityTestRule<SignUpActivity> rule =
            new ActivityTestRule<>(SignUpActivity.class, true, true);


    /**
     * Runs before all tests and creates solo instance.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());
    }

    /**
     * Gets the Activity
     *
     * @throws Exception
     */
    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();
    }

    /**
     * Check cancel button
     *
     */
    @Test
    public void checkCancelButton(){
        solo.assertCurrentActivity("Wrong Activity", SignUpActivity.class);
        solo.clickOnButton("Cancel");
        solo.assertCurrentActivity("Test failed",MainActivity.class);
    }

    /**
     * Check if an empty username can signup as a new account or not
     *
     */
    @Test
    public void checkUsername(){
        solo.assertCurrentActivity("Wrong Activity", SignUpActivity.class);
        solo.enterText((EditText) solo.getView(R.id.sign_up_password), "emptyUsername");
        solo.clickOnButton("Confirm");
        solo.assertCurrentActivity("Test failed",SignUpActivity.class);

    }

    /**
     * Check if an empty password can signup as a new account or not
     */
    @Test
    public void checkPassword(){
        solo.assertCurrentActivity("Wrong Activity", SignUpActivity.class);
        solo.enterText((EditText) solo.getView(R.id.sign_up_username), "emptyPassword");
        solo.clickOnButton("Confirm");
        solo.assertCurrentActivity("Test failed",SignUpActivity.class);

    }


    /**
     * Check if an existed account can be signup or not
     */
    @Test
    public void checkExistence(){
        solo.assertCurrentActivity("Wrong Activity", SignUpActivity.class);
        solo.enterText((EditText) solo.getView(R.id.sign_up_username), "Player1");
        solo.enterText((EditText) solo.getView(R.id.sign_up_password), "123");
        solo.enterText((EditText) solo.getView(R.id.sign_up_name), "Player1_full_name");
        solo.enterText((EditText) solo.getView(R.id.sign_up_email_address), "Player1_email_address");
        solo.clickOnButton("Confirm");
        solo.assertCurrentActivity("Test failed",SignUpActivity.class);

    }


    /**
     * Check the confirm button
     */
    @Test
    public void checkConfirmButton(){
        solo.assertCurrentActivity("Wrong Activity", SignUpActivity.class);
        solo.enterText((EditText) solo.getView(R.id.sign_up_username), "Player999");
        solo.enterText((EditText) solo.getView(R.id.sign_up_password), "321");
        solo.enterText((EditText) solo.getView(R.id.sign_up_name), "Player999_full_name");
        solo.enterText((EditText) solo.getView(R.id.sign_up_email_address), "Player999_email_address");
        solo.clickOnButton("Confirm");
        solo.assertCurrentActivity("Test failed",MainActivity.class);

    }
}
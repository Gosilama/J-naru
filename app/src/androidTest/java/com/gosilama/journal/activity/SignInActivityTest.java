package com.gosilama.journal.activity;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.gosilama.journal.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.gosilama.journal.testUtil.TestUtil.testEditTexts;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignInActivityTest {

    @Rule
    public ActivityTestRule<SignInActivity> mActivityRule =
            new ActivityTestRule<>(SignInActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.sign_in_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void testWelcomeMessage() {
        onView(withId(R.id.text_view_welcome_message)).check(matches(isDisplayed()));
    }

    @Test
    public void testEmailEditText() {
        testEditTexts(R.id.edit_text_email,
                "testemail@email.com",
                "testemail2@email.com");
    }

    @Test
    public void testPasswordEditText() {
        testEditTexts(R.id.edit_text_password,
                "password1",
                "password2");
    }

    @Test
    public void testSignInButton() {
        onView(withId(R.id.button_sign_in)).perform(click());
    }

    @Test
    public void testSignUpLink() {
        onView(withId(R.id.text_view_sign_up)).check(matches(isClickable()));
        onView(withId(R.id.text_view_sign_up)).perform(click());
        testRegistrationDialog();
        testRegisterEmailEditText();
        testRegisterPasswordEditText();
        testRegisterPasswordConfirmEditText();
        testRegisterButton();
    }

    public void testRegistrationDialog() {
        onView(withId(R.id.registration_dialog)).check(matches(isDisplayed()));
    }

    public void testRegisterEmailEditText() {
        testEditTexts(R.id.edit_text_register_email,
                "testregisteremail@email.com",
                "testregisteremail2@email.com");
    }

    public void testRegisterPasswordEditText() {
        testEditTexts(R.id.edit_text_register_password,
                "passwordRegister1",
                "passwordRegister1");
    }

    public void testRegisterPasswordConfirmEditText() {
        testEditTexts(R.id.edit_text_confirm_password,
                "passwordRegister1",
                "passwordRegister1");
    }

    public void testRegisterButton() {
        onView(withId(R.id.button_register)).perform(click());
    }
}
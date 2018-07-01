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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.gosilama.journal.testUtil.TestUtil.testEditTexts;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class JournalActivityTest {

    @Rule
    public ActivityTestRule<JournalActivity> mActivityRule =
            new ActivityTestRule<>(JournalActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.journal_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void testEntryTitleEditText() {
        testEditTexts(R.id.edit_text_journal_entry_title,
                "Test Entry Title",
                "Test entry title 2");
    }

    @Test
    public void testEntryContentEditText() {
        testEditTexts(R.id.edit_text_journal_entry_content,
                "Test journal entry",
                "Test journal entry 2");
    }

    @Test
    public void testSaveEntryFloatingActionButton() {
        onView(withId(R.id.floating_action_button_save)).check(matches(isDisplayed()));
        onView(withId(R.id.floating_action_button_save)).check(matches(isClickable()));
        onView(withId(R.id.floating_action_button_save)).perform(click());
    }
}
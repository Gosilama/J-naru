package com.gosilama.journal.testUtil;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public final class TestUtil {
    public static void testEditTexts(int viewId, String testString, String replaceTestString) {
        onView(withId(viewId)).check(matches(isFocusable()));
        onView(withId(viewId)).perform(clearText());
        onView(withId(viewId)).perform(typeText(testString));
        onView(withId(viewId)).perform(replaceText(replaceTestString));
        onView(withId(viewId)).perform(closeSoftKeyboard());
    }
}

package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new
            ActivityScenarioRule<MainActivity>(MainActivity.class);

    /**
     * Test case 1: Check if the activity correctly switched to ShowActivity
     * I will test this by checking if clicking on a city in the list launches ShowActivity
     */
    @Test
    public void testActivitySwitched() {
        // Add city to the list first
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list
        onData(is (instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        //Verify that ShowActivity is launched
        onView(withId(R.id.city_name)).check(matches(withText("Edmonton")));

    }

    /**
     * Test case 2: Check weather the city name is consistent with the city name in the list
     * I will test this by checking if the city name in the list matches the city name in the ShowActivity
     */
    @Test
    public void testCityName() {
        String testCity = "Vancouver";

        //Add city to the list first
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText(testCity));
        onView(withId(R.id.button_confirm)).perform(click());

        //Click on the city in the list
        onData(is (instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Verify that the city name in the list matches the city name in the ShowActivity
        onView(withId(R.id.city_name)).check(matches(withText(testCity)));
        onView(withId(R.id.city_name)).check(matches(isDisplayed()));

    }

    /**
     * Test case 3: Check if the back button works correctly
     * I will test this by checking if clicking on the back button returns to the MainActivity
     */
    @Test
    public void testBackButton() {
        // Add city to the list first
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Toronto"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list
        onData(is (instanceOf(String.class)))
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Click on the back button
        onView(withId(R.id.button_back)).check(matches(isDisplayed())).perform(click());

        // Verify that MainActivity is launched
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));


    }


}

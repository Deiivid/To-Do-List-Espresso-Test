package es.deiividdev.com.to_do_list_espresso_test

import android.graphics.Point
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SecondActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(SecondActivity::class.java)

    /**
     * Test to show and dismiss the dialog by clicking the OK button.
     */
    @Test
    fun shouldShowAndDismissDialog() {
        // Click on the button that shows the dialog
        onView(withId(R.id.dialogButton)).perform(click())

        // Check if the dialog is displayed
        onView(withText("This is a test dialog")).check(matches(isDisplayed()))

        // Click the OK button on the dialog
        onView(withText("OK")).perform(click())

        // Verify that the dialog is no longer displayed
        onView(withText("This is a test dialog")).check(doesNotExist())
    }

    /**
     * Test to show and dismiss the dialog by clicking the Cancel button.
     */
    @Test
    fun shouldShowAndCancelDialog() {
        // Click on the button that shows the dialog
        onView(withId(R.id.dialogButton)).perform(click())

        // Check if the dialog is displayed
        onView(withText("This is a test dialog")).check(matches(isDisplayed()))

        // Click the Cancel button on the dialog
        onView(withText("Cancel")).perform(click())

        // Verify that the dialog is no longer displayed
        onView(withText("This is a test dialog")).check(doesNotExist())
    }

    /**
     * Test to dismiss the dialog by clicking outside of it.
     */
    @Test
    fun shouldDismissDialogOnOutsideTouch() {
        // Click on the button that shows the dialog
        onView(withId(R.id.dialogButton)).perform(click())

        // Check if the dialog is displayed
        onView(withText("This is a test dialog")).check(matches(isDisplayed()))

        // Get an instance of UiDevice
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Get the screen size
        val displaySize = Point()
        device.displaySizeDp.let {
            displaySize.x = it.x
            displaySize.y = it.y
        }

        // Click on a position outside the dialog (near the bottom-right corner)
        device.click(displaySize.x - 10, displaySize.y - 10)

        // Verify that the dialog is no longer displayed
        onView(withText("This is a test dialog")).check(doesNotExist())
    }
}

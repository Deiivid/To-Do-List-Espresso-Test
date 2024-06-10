package es.deiividdev.com.to_do_list_espresso_test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.deiividdev.com.to_do_list_espresso_test.adapter.TaskAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    /* If it's needed you can add permissions for the test to work
    @get:Rule
     var permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
         android.Manifest.permission.ACCESS_FINE_LOCATION,
         android.Manifest.permission.ACCESS_COARSE_LOCATION
     )*/

    /**
     * Test to add a task and verify it has been added to the list.
     */
    @Test
    fun shouldAddTask() {
        val taskName = "New Task" // val for task name used below
        // Add text to the input field and close the soft keyboard
        onView(withId(R.id.inputTask)).perform(typeText(taskName), closeSoftKeyboard())
        // Click the add button
        onView(withId(R.id.addButton)).perform(click())
        // Check if the task has been added to the list
        onView(withText(taskName)).check(matches(isDisplayed()))
    }

    /**
     * Test to add a task, mark it as complete, and verify it is marked as completed.
     */
    @Test
    fun shouldCompleteTask() {
        val taskName = "Complete Task" // val for task name used below
        // Add text to the input field and close the soft keyboard
        onView(withId(R.id.inputTask)).perform(typeText(taskName), closeSoftKeyboard())
        // Click the add button
        onView(withId(R.id.addButton)).perform(click())
        // Click on the task in the list
        onView(withText(taskName)).perform(click())
        // Click the checkbox to mark the task as completed
        onView(withId(R.id.taskCheckbox)).perform(click())
        // Check if the task is marked as completed
        onView(withId(R.id.taskCheckbox)).check(matches(isChecked()))
    }

    /**
     * Test to add a task, delete it, and verify it has been removed from the list.
     */
    @Test
    fun shouldDeleteTask() {
        val taskName = "Delete Task" // val for task name used below
        // Add text to the input field and close the soft keyboard
        onView(withId(R.id.inputTask)).perform(typeText(taskName), closeSoftKeyboard())
        // Click the add button
        onView(withId(R.id.addButton)).perform(click())
        // Click on the task in the list
        onView(withText(taskName)).perform(click())
        // Click the delete button to remove the task
        onView(withId(R.id.deleteButton)).perform(click())
        // Check if the task has been removed from the list
        onView(withText(taskName)).check(doesNotExist())
    }

    /**
     * Test to scroll to the end of the task list and verify the last item is displayed.
     */
    @Test
    fun shouldScrollToEndOfList() {
        // Scroll to the last item in the list and check if it is displayed
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition<TaskAdapter.TaskViewHolder>(99))
        // Check if the last task in the list is displayed
        onView(withText("Task 100")).check(matches(isDisplayed()))
    }
}

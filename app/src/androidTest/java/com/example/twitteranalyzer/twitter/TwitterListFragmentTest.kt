package com.example.twitteranalyzer.twitter

import android.view.View
import androidx.annotation.NonNull
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.twitteranalyzer.MainActivity
import com.example.twitteranalyzer.R
import com.example.twitteranalyzer.di.viewModelModule
import com.example.twitteranalyzer.fakeTwitterRepository
import com.example.twitteranalyzer.network.TwitterModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito
import util.DataBindingIdlingResource
import util.EspressoIdlingResource
import util.monitorActivity
import util.monitorFragment


@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class TwitterListFragmentTest :
    AutoCloseKoinTest() {


    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            modules(
                listOf(
                    fakeTwitterRepository,
                    viewModelModule
                )
            )
        }
    }

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun twitterList_WithItems() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        // GIVEN - On the home screen
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.search_text))))
            .perform(typeText("luan"))
        runBlocking {
            onView(withId(R.id.twitter_recycler))
                .check(matches(atPosition(0, withText("luan"), R.id.user_name)));
        }
    }

    @Test
    fun navigationToTwitterAnalyze() {

        val scenario =
            launchFragmentInContainer<TwitterListFragment>(null, R.style.Theme_TwitterAnalyzer)
        dataBindingIdlingResource.monitorFragment(scenario)
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.search_text))))
            .perform(typeText("luan"))
        runBlocking {
            onView(withId(R.id.twitter_recycler))
                .check(matches(atPosition(0, withText("luan"), R.id.user_name)));
        }
        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        onView(withId(R.id.twitter_recycler))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Mockito.verify(navController).navigate(
            TwitterListFragmentDirections.actionShowAnalyzerFrament(
                TwitterModel(
                    1,
                    "mock",
                    "luan",
                    "teste.url"
                )
            )
        )
    }

    @After
    fun after() {
        stopKoin()
    }


    fun atPosition(
        position: Int, itemMatcher: Matcher<View?>,
        @NonNull targetViewId: Int
    ): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has view id $itemMatcher at position $position")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                val targetView: View = viewHolder!!.itemView.findViewById(targetViewId)
                return itemMatcher.matches(targetView)
            }
        }
    }


}
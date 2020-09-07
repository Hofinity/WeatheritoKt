package com.hofinity.weatherito.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.hofinity.weatherito.R
import com.hofinity.weatherito.home.Delay.waitFor
import com.hofinity.weatherito.presentation.ui.component.cityPicker.viewModels.CityPickerViewModel.Companion.cityList
import com.hofinity.weatherito.presentation.ui.component.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class HomeActivityTest {
    private val testSearchString = "President"

    @get:Rule
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java, true)

    private var citiesList = cityList

    private val cityListRandomPosition = (0 until citiesList.size).random()

    private val cityListRandomTitle = citiesList[cityListRandomPosition].title

    private var mIdlingResource: IdlingResource? = null

//    @Before
//    fun setup() {
//        IdlingPolicies.setMasterPolicyTimeout(60 * 1000 * 3,
//            java.util.concurrent.TimeUnit.MILLISECONDS
//        )
//        IdlingPolicies.setIdlingResourceTimeout(60 * 1000 * 3,
//            java.util.concurrent.TimeUnit.MILLISECONDS
//        )
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
//    }

    @Test
    fun testCitiesList() {
        onView(withId(R.id.currentCityNameTv)).perform(click())
//        onView(withText("Isfahan")).perform(click())
//        onView(withId(R.id.cityRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(10, click()))

        onView(isRoot()).perform(waitFor(1000))

//        onView(withId(R.id.cityRecyclerView)).perform(
//            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
//                hasDescendant(withText(cityListRandomTitle))))

        onView(withId(R.id.cityRecyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(cityListRandomPosition))

        onView(withId(R.id.cityRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(cityListRandomPosition, click()))
    }

    @Test
    fun testTodayForecast() {

        onView(withId(R.id.todayWeatherRecyclerView)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                hasDescendant(withText("10AM"))))

        onView(withId(R.id.todayWeatherRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))

        onView(isRoot()).perform(waitFor(1000))

        onView(withId(R.id.dimView)).perform(click())
    }

    @Test
    fun testLastWeekForecast() {

        onView(withId(R.id.lastWeekWeatherRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.dimView)).perform(click())
    }

//    @Test
//    fun testScroll() {
//        onView(withId(R.id.rv_news_list))
//                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun testRefresh() {
//        //Before refresh there is a list .
//        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()))
//        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
//        // do refresh .
//        onView(withId(R.id.action_favorite)).perform(click())
//        //after refresh there is a list.
//        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()))
//        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
//    }
//
//    @Test
//    fun displayNewsData() {
//        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()))
//        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
//    }

//    @After
//    fun unregisterIdlingResource() {
//        if(mIdlingResource != null) IdlingRegistry.getInstance().unregister()
//    }

}
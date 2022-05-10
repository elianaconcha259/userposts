package com.example.shoppingapp.presentation.ui


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.shoppingapp.R
import org.hamcrest.Matchers.allOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class ProductFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun verifyTitleNameAndProductListInProductFragment() {
        val textView = onView(
            allOf(
                withText("ShoppingApp"),
                withParent(
                    allOf(
                        withId(R.id.action_bar),
                        withParent(withId(R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("ShoppingApp")))

        Thread.sleep(5000)

        val recyclerView = onView(
            allOf(
                withId(R.id.rvProductList),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))
        val count = getRecyclerViewCount()
        Assert.assertTrue("The product list has elements", count > 0)
    }

    private fun getRecyclerViewCount(): Int {
        val recyclerView =
            mActivityTestRule.activity.findViewById(R.id.rvProductList) as RecyclerView
        return recyclerView.adapter?.itemCount ?: 0
    }
}

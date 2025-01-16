package com.iug.coursehub.feature.user.home_screen.cuorse_categories_screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.iug.coursehub.R
import com.iug.coursehub.feature.user.home_screen.cuorse_categories_screen.course_list_screen.CourseListFragment

class CategoriesPagerAdapter(
    activity: FragmentActivity,
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    private val categories = activity
        .resources
        .getStringArray(R.array.course_categories)

    private val fragments = categories.map {
        CourseListFragment.newInstance(it)
    }.toMutableList()
        .apply {
            removeAt(0)
        }.toList()

    private val titles = categories
        .toMutableList().apply {
            removeAt(0)
        }.toList()

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int) = titles[position]
}
package com.project.leverxtask.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class DetailNewsPagerAdapter(fm: FragmentManager, fragments: ArrayList<Fragment>) :
    FragmentStatePagerAdapter(fm) {
    private var fragment = ArrayList<Fragment>()

    override fun getCount(): Int {
        return fragment.size
    }

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    init {
        fragment = fragments
    }
}
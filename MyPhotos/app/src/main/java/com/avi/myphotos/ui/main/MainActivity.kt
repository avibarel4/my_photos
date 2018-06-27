package com.avi.myphotos.ui.main

import com.avi.myphotos.R
import com.avi.myphotos.base.activity.BaseActivity
import com.avi.myphotos.base.fragment.BaseFragment
import com.avi.myphotos.base.fragment.FragmentAnimation

/**
 * Created by avi.barel on 27/06/2018.
 */
class MainActivity : BaseActivity<MainActivityViewModel, MainActivityViewImpl>() {

    override fun provideActivityView(): MainActivityViewImpl {
        return MainActivityViewImpl(this, null)
    }

    override fun provideViewModel(): Class<MainActivityViewModel> {
        return MainActivityViewModel::class.java
    }

    fun openOverlayFragment(fragment: BaseFragment<*, *>) {
        newFragment(
            fragment,
            replace = false,
            addToStack = true,
            tag = fragment.getFragmentTag(),
            fragmentAnimation = FragmentAnimation(R.anim.enter_from_bottom, 0, 0, R.anim.exit_to_bottom))
    }

}

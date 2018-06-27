package com.avi.myphotos.base.fragment

import android.support.annotation.AnimRes

/**
 * Created by avi.barel on 27/06/2018.
 */
data class FragmentAnimation(
    @AnimRes val animEnter: Int = 0,
    @AnimRes val animExit: Int = 0,
    @AnimRes val animPopEnter: Int = 0,
    @AnimRes val animPopExit: Int = 0)
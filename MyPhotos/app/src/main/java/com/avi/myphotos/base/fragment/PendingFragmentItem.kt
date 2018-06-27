package com.avi.myphotos.base.fragment

import android.os.Bundle

/**
 * Created by avi.barel on 27/06/2018.
 */
data class PendingFragmentItem(
    val pendingFragmentType: PendingFragmentType,
    val replace: Boolean = true,
    val addToStack: Boolean = true,
    val tag: String? = null,
    val clearStack: Boolean = false,
    val fragmentAnimation: FragmentAnimation? = null,
    val executeImmediately: Boolean = false,
    val bundle: Bundle? = null)
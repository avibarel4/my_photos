package com.avi.myphotos.base.activity

import android.support.annotation.IdRes
import android.support.annotation.Nullable
import com.avi.myphotos.base.IMVCView
import com.avi.myphotos.base.fragment.BaseFragment

/**
 * Created by avi.barel on 27/06/2018.
 */
interface IBaseActivityView : IMVCView {

    @IdRes
    fun containerId(): Int

    @Nullable
    fun initialFragment(): BaseFragment<*,*>?

}
package com.matsunaopen.repositoryviewer.util

import android.content.Context
import android.net.ConnectivityManager
import com.matsunaopen.repositoryviewer.preference.ConstValues
import com.matsunaopen.repositoryviewer.preference.PreferenceUtils

/**
 * Created by DevUser on 2018/10/08.
 */
class EnvironmentUtils {
    fun isNetworkEnable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info?.isConnected ?: false
    }

    fun isMock(context: Context): Boolean = PreferenceUtils.getBoolean(context, ConstValues.IS_MOCH_KEY)
}

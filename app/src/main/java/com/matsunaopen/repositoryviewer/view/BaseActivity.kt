package com.matsunaopen.repositoryviewer.view

import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.matsunaopen.repositoryviewer.R
import com.matsunaopen.repositoryviewer.preference.ConstValues.IS_MOCH_KEY
import com.matsunaopen.repositoryviewer.preference.PreferenceUtils
import com.matsunaopen.repositoryviewer.viewmodel.BaseViewModel

/**
 * Created by DevUser on 2018/10/07.
 */
open class BaseActivity : AppCompatActivity() {
    // TODO ViewModelの参照をDI
    private val viewModel = BaseViewModel()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_select_user, menu)
        val showLogFlag = PreferenceUtils.getBoolean(this, IS_MOCH_KEY)
        menu.findItem(R.id.setting_action_switch_mock).title = getMenuTitle(showLogFlag)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting_action_clear_db -> {
                viewModel.fapCleanDB()
            }
            R.id.setting_action_switch_mock -> {
                val showLogFlag = PreferenceUtils.getBoolean(this, IS_MOCH_KEY)
                PreferenceUtils.putBoolean(this, IS_MOCH_KEY, !showLogFlag)
                item.title = getMenuTitle(!showLogFlag)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getMenuTitle(flag: Boolean): String =
            if (!flag)
                getString(R.string.setting_mock_state_off)
            else
                getString(R.string.setting_mock_state_on)
}
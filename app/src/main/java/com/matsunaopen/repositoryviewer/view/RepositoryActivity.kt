package com.matsunaopen.repositoryviewer.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.matsunaopen.repositoryviewer.DisplayAreaController
import com.matsunaopen.repositoryviewer.R
import com.matsunaopen.repositoryviewer.data.RepositoryData
import com.matsunaopen.repositoryviewer.databinding.ActivitySelectUserBinding
import com.matsunaopen.repositoryviewer.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.activity_select_user.*
import android.support.design.widget.Snackbar
import com.matsunaopen.repositoryviewer.util.EnvironmentUtils

class RepositoryActivity : BaseActivity() {
    lateinit var displayController: DisplayAreaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_user)

        val binding = DataBindingUtil.setContentView<ActivitySelectUserBinding>(this, R.layout.activity_select_user)
        binding.viewModel = RepositoryViewModel(this, getRepositoryCallback())

        displayController = DisplayAreaController(getRepositoryCallback())
        binding.showAreaRepository.apply {
            adapter = displayController.adapter
            setController(displayController)
        }

        binding.viewModel?.let {
            it.resultField.get().let {
                displayController.setData("", it)
            }
        }

        val adapter = ArrayAdapter<String>(this, R.layout.list_item, listOf("MatsuNaoPen", "airbnb"))
        target_user_name_input_area.apply {
            threshold = 1
            setAdapter(adapter)
        }
    }

    // TODO RecyclerViewにDataBindingで更新データ渡しがしたい
    interface RepositoryUpdateCallback {
        fun updateRepository(userName: String, data: List<RepositoryData>)

        fun onClickRepository(url: String)
    }

    private fun getRepositoryCallback(): RepositoryUpdateCallback = object : RepositoryUpdateCallback {
        override fun onClickRepository(url: String) {
            if (EnvironmentUtils().isNetworkEnable(applicationContext)) {
                val dialog = RepositoryDialog()
                val args = Bundle()
                args.putString(dialog.URL_KEY, url)
                dialog.arguments = args
                dialog.show(fragmentManager, "repositoryDialog")
                Log.d("test", "url:" + url)
            } else {
                showSnackBar()
            }
        }

        override fun updateRepository(userName: String, data: List<RepositoryData>) {
            displayController.setData(userName, data)
        }
    }

    fun showSnackBar() {
        Snackbar.make(this.currentFocus, "network not connected", Snackbar.LENGTH_SHORT).show()
    }
}



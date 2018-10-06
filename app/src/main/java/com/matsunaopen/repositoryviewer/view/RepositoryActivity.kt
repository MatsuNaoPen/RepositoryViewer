package com.matsunaopen.repositoryviewer.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.matsunaopen.repositoryviewer.R
import com.matsunaopen.repositoryviewer.databinding.ActivitySelectUserBinding
import com.matsunaopen.repositoryviewer.viewmodel.RepositoryViewModel

class RepositoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_user)

        val binding = DataBindingUtil.setContentView<ActivitySelectUserBinding>(this, R.layout.activity_select_user)
        binding.viewModel = RepositoryViewModel()
    }

}

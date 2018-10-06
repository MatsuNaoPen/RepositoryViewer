package com.matsunaopen.repositoryviewer.viewmodel

import android.databinding.Bindable
import android.databinding.ObservableField
import android.databinding.ViewDataBinding
import android.util.Log
import com.matsunaopen.repositoryviewer.data.RepositoryData
import com.matsunaopen.repositoryviewer.model.repository.GetUsersRepository
import com.matsunaopen.repositoryviewer.view.RepositoryActivity
import rx.Observer

/**
 * Created by DevUser on 2018/10/05.
 */
class RepositoryViewModel(val callback: RepositoryActivity.RepositoryUpdateCallback) {
    var userName = ObservableField<String>("")
    var resultField = ObservableField<List<RepositoryData>>(listOf())

    private val getUserRepository = GetUsersRepository()
    fun tapStart() {
        if (userName.get().isNotBlank()) {
            getUserRepository.getUsersRepository(userName.get(), getUserNameObserver())
        } else {
            Log.d("test", "userName is Blank")
        }
    }

    private fun getUserNameObserver(): Observer<List<RepositoryData>> = object : Observer<List<RepositoryData>> {
        override fun onCompleted() {
        }

        override fun onError(e: Throwable?) {
        }

        override fun onNext(t: List<RepositoryData>) {
            callback.updateRepository(userName.get(), t)
        }
    }
}
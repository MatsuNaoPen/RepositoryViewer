package com.matsunaopen.repositoryviewer.viewmodel

import android.databinding.ObservableField
import android.util.Log
import com.matsunaopen.repositoryviewer.RepositoryBehavior
import com.matsunaopen.repositoryviewer.data.RepositoryData
import com.matsunaopen.repositoryviewer.model.repository.IGetUsersRepository
import com.matsunaopen.repositoryviewer.view.RepositoryActivity

/**
 * Created by DevUser on 2018/10/05.
 */
class RepositoryViewModel(private val behavior: RepositoryBehavior,
                          val callback: RepositoryActivity.RepositoryUpdateCallback,
                          private val repository: IGetUsersRepository) {
    var userName = ObservableField<String>("")
    var resultField = ObservableField<List<RepositoryData>>(listOf())

    fun tapStart() {
        if (userName.get().isNotBlank()) {
            repository.getUsersRepository(userName.get(), behavior, callback)
        } else {
            Log.d("test", "userName is Blank")
        }
    }
}
package com.matsunaopen.repositoryviewer.viewmodel

import android.databinding.ObservableField
import android.util.Log
import com.matsunaopen.repositoryviewer.data.RepositoryDataList
import com.matsunaopen.repositoryviewer.model.repository.GetUsersRepository
import rx.Observer

/**
 * Created by DevUser on 2018/10/05.
 */
class RepositoryViewModel {
    var userName = ObservableField<String>("")
    var resultField = ObservableField<String>()

    val getUserRepository = GetUsersRepository()
    fun tapStart() {
        if (userName.get().isNotBlank()) {
            getUserRepository.getUsersRepository(userName.get(), getUserNameObserver())
        } else {
            Log.d("test", "userName is Blank")
        }
    }

    fun getUserNameObserver(): Observer<RepositoryDataList> = object : Observer<RepositoryDataList> {
        override fun onCompleted() {
        }

        override fun onError(e: Throwable?) {
        }

        override fun onNext(t: RepositoryDataList) {
            val builder = StringBuilder();
            for (data in t.list) {
                builder.append(data.name)
                builder.append(":")
                builder.append(data.url)
                builder.append("\r\n")
            }
            resultField.set(builder.toString())
        }
    }
}
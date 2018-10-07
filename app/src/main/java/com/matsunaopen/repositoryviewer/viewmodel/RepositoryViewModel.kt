package com.matsunaopen.repositoryviewer.viewmodel

import android.databinding.ObservableField
import android.util.Log
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.factory
import com.matsunaopen.repositoryviewer.data.RepositoryData
import com.matsunaopen.repositoryviewer.di.GetUsersRepositoryFactory
import com.matsunaopen.repositoryviewer.model.repository.IGetUsersRepository
import com.matsunaopen.repositoryviewer.view.RepositoryActivity
import rx.Observer

/**
 * Created by DevUser on 2018/10/05.
 */
class RepositoryViewModel(val callback: RepositoryActivity.RepositoryUpdateCallback) {
    var userName = ObservableField<String>("")
    var resultField = ObservableField<List<RepositoryData>>(listOf())

    private val getRepositoryFactory = Kodein {
        bind<IGetUsersRepository>() with factory { type: Int -> GetUsersRepositoryFactory.calling(type) }
    }

    fun tapStart() {
        if (userName.get().isNotBlank()) {
            getRepositoryFactory.factory<Int, IGetUsersRepository>().invoke(1).getUsersRepository(userName.get(), getUserNameObserver())
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
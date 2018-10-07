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
class RepositoryViewModel(val isMock: Boolean, val callback: RepositoryActivity.RepositoryUpdateCallback) {
    var userName = ObservableField<String>("")
    var resultField = ObservableField<List<RepositoryData>>(listOf())

    private val getRepositoryFactory = Kodein {
        bind<IGetUsersRepository>() with factory { isMock: Boolean -> GetUsersRepositoryFactory.calling(isMock) }
    }

    fun tapStart() {
        if (userName.get().isNotBlank()) {
            // コンストラクタでisMockの取得を行っているため、タップ時のステータスが正しく反映されない
            getRepositoryFactory.factory<Boolean, IGetUsersRepository>().invoke(isMock).getUsersRepository(userName.get(), getUserNameObserver())
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
package com.matsunaopen.repositoryviewer.model.repository

import com.matsunaopen.repositoryviewer.data.RepositoryData
import com.matsunaopen.repositoryviewer.db.RepositoryDao
import rx.Observer

/**
 * Created by DevUser on 2018/10/06.
 */
class GetUsersRepositoryFromLocal : IGetUsersRepository {
    override fun getUsersRepository(userName: String, observable: Observer<List<RepositoryData>>) {
        if (RepositoryDao().find(userName) > 0) {
            val data = RepositoryDao().get(userName)
            observable.onNext(data)
        } else {
            observable.onNext(emptyList())
        }
    }
}
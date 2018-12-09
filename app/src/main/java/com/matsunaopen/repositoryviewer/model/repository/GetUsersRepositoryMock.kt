package com.matsunaopen.repositoryviewer.model.repository

import com.matsunaopen.repositoryviewer.RepositoryBehavior
import com.matsunaopen.repositoryviewer.data.RepositoryData
import com.matsunaopen.repositoryviewer.di.GetUsersRepositoryFactory
import com.matsunaopen.repositoryviewer.view.RepositoryActivity
import rx.Observer

/**
 * Created by DevUser on 2018/10/07.
 */
class GetUsersRepositoryMock : IGetUsersRepository {
    override fun getUsersRepository(userName: String,
                                    behavior: RepositoryBehavior,
                                    callback: RepositoryActivity.RepositoryUpdateCallback) {
        val result = (100..105).map {
            RepositoryData(
                    userName,
                    "repository" + it.toString(),
                    "https://www.google.com",
                    "https://www.google.com",
                    it.toLong())
        }

        callback.updateRepository("$userName(Mock)", result)
    }
}

package com.matsunaopen.repositoryviewer.di

import com.matsunaopen.repositoryviewer.RepositoryBehavior
import com.matsunaopen.repositoryviewer.RepositoryBehavior.*
import com.matsunaopen.repositoryviewer.model.repository.GetUsersRepositoryFromLocal
import com.matsunaopen.repositoryviewer.model.repository.GetUsersRepositoryFromNetwork
import com.matsunaopen.repositoryviewer.model.repository.GetUsersRepositoryMock
import com.matsunaopen.repositoryviewer.model.repository.IGetUsersRepository

/**
 * Created by DevUser on 2018/10/07.
 */
object GetUsersRepositoryFactory {
    fun calling(behavior: RepositoryBehavior): IGetUsersRepository =
            when (behavior) {
                MOCK -> GetUsersRepositoryMock()
                LOCAL -> GetUsersRepositoryFromLocal()
                NETWORK -> GetUsersRepositoryFromNetwork()
            }
}
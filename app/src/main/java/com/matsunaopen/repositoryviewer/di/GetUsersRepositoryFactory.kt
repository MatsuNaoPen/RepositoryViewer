package com.matsunaopen.repositoryviewer.di

import com.matsunaopen.repositoryviewer.model.repository.GetUsersRepositoryFromLocal
import com.matsunaopen.repositoryviewer.model.repository.GetUsersRepositoryFromNetwork
import com.matsunaopen.repositoryviewer.model.repository.GetUsersRepositoryMock
import com.matsunaopen.repositoryviewer.model.repository.IGetUsersRepository

/**
 * Created by DevUser on 2018/10/07.
 */
object GetUsersRepositoryFactory {
    fun calling(behavior: Behavior): IGetUsersRepository =
            when (behavior) {
                Behavior.MOCK -> GetUsersRepositoryMock()
                Behavior.LOCAL -> GetUsersRepositoryFromLocal()
                Behavior.NETWORK -> GetUsersRepositoryFromNetwork()
            }

    enum class Behavior {
        MOCK,
        NETWORK,
        LOCAL
    }

    fun getBehavior(isMock: Boolean, isNetworkConnected: Boolean): Behavior {
        return if (isMock) {
            Behavior.MOCK
        } else {
            if (isNetworkConnected) {
                Behavior.NETWORK
            } else {
                Behavior.LOCAL
            }
        }
    }

}
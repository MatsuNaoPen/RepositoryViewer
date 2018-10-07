package com.matsunaopen.repositoryviewer.di

import com.matsunaopen.repositoryviewer.model.repository.GetUsersRepository
import com.matsunaopen.repositoryviewer.model.repository.GetUsersRepositoryMock
import com.matsunaopen.repositoryviewer.model.repository.IGetUsersRepository

/**
 * Created by DevUser on 2018/10/07.
 */
object GetUsersRepositoryFactory {
    fun calling(type: Int): IGetUsersRepository {
//        return GetUsersRepository()
        return GetUsersRepositoryMock()
    }
}
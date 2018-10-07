package com.matsunaopen.repositoryviewer.model.repository

import com.matsunaopen.repositoryviewer.data.RepositoryData
import rx.Observer

/**
 * Created by DevUser on 2018/10/07.
 */
interface IGetUsersRepository {
    fun getUsersRepository(userName: String, observable: Observer<List<RepositoryData>>)
}
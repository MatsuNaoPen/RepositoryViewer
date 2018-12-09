package com.matsunaopen.repositoryviewer.model.repository

import com.matsunaopen.repositoryviewer.RepositoryBehavior
import com.matsunaopen.repositoryviewer.view.RepositoryActivity

/**
 * Created by DevUser on 2018/10/07.
 */
interface IGetUsersRepository {
    fun getUsersRepository(userName: String,
                           behavior: RepositoryBehavior,
                           callback: RepositoryActivity.RepositoryUpdateCallback)
}
package com.matsunaopen.repositoryviewer.model.repository

import com.matsunaopen.repositoryviewer.RepositoryBehavior
import com.matsunaopen.repositoryviewer.db.RepositoryDao
import com.matsunaopen.repositoryviewer.view.RepositoryActivity

/**
 * Created by DevUser on 2018/10/06.
 */
class GetUsersRepositoryFromLocal : IGetUsersRepository {
    override fun getUsersRepository(userName: String,
                                    behavior: RepositoryBehavior,
                                    callback: RepositoryActivity.RepositoryUpdateCallback) {
        val data = if (RepositoryDao().find(userName) > 0) {
            RepositoryDao().get(userName)
        } else {
            emptyList()
        }

        callback.updateRepository("$userName (pastData)", data)
    }
}
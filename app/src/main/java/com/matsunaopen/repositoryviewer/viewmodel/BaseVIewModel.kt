package com.matsunaopen.repositoryviewer.viewmodel

import com.matsunaopen.repositoryviewer.db.RepositoryDao

/**
 * Created by DevUser on 2018/10/07.
 */
class BaseViewModel {
    fun fapCleanDB() {
        RepositoryDao().deleteAll()
    }
}
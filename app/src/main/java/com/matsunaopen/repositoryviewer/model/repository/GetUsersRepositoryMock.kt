package com.matsunaopen.repositoryviewer.model.repository

import com.matsunaopen.repositoryviewer.data.RepositoryData
import rx.Observer

/**
 * Created by DevUser on 2018/10/07.
 */
class GetUsersRepositoryMock :IGetUsersRepository{
    override fun getUsersRepository(userName: String, observable: Observer<List<RepositoryData>>) {
        var result= mutableListOf<RepositoryData>()
        for(i in 100..105){
            result.add(RepositoryData(
                    "repository" + i.toString(),
                    "https://www.google.com",
                    "https://www.google.com",
                    i.toLong()))
        }

        observable.onNext(result)
    }
}

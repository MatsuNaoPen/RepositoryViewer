package com.matsunaopen.repositoryviewer.model

import com.matsunaopen.repositoryviewer.model.response.ResponseGetRepos
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by DevUser on 2018/10/05.
 */
interface GetRepositoryService {
    @GET("users/{userName}/repos")
    fun schedule(@Path("userName") userName: String): Observable<List<ResponseGetRepos>>
}
package com.matsunaopen.repositoryviewer.model

import retrofit2.http.GET
import java.util.*

/**
 * Created by DevUser on 2018/10/05.
 */
interface GetUserService{
    @GET("users/{user}")
    fun scheduled():Observable
}
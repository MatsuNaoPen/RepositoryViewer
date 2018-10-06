package com.matsunaopen.repositoryviewer.model.response

import java.io.Serializable

/**
 * Created by DevUser on 2018/10/05.
 */
data class ResponseGetRepos(
        val id: String,
        val full_name: String,
        val name: String,
        val html_url: String,
        val description: String
) : Serializable
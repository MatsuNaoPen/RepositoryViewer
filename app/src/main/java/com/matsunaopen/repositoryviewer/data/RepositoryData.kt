package com.matsunaopen.repositoryviewer.data

import java.io.Serializable

/**
 * Created by DevUser on 2018/10/06.
 */
data class RepositoryData(
        val name: String,
        val url: String
) : Serializable
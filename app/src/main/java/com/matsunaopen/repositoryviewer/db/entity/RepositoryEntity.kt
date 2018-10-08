package com.matsunaopen.repositoryviewer.db.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by DevUser on 2018/10/06.
 */
open class RepositoryEntity : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    lateinit var userName: String
    lateinit var name: String
    lateinit var url: String
    lateinit var readmeUrl: String
}

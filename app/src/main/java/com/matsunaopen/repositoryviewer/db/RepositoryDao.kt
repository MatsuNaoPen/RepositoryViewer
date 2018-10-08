package com.matsunaopen.repositoryviewer.db

import com.matsunaopen.repositoryviewer.data.RepositoryData
import com.matsunaopen.repositoryviewer.db.entity.RepositoryEntity
import io.realm.Realm

/**
 * Created by DevUser on 2018/10/08.
 */
open class RepositoryDao {
    fun create(list: List<RepositoryData>) {
        list.forEach { create(it) }
    }

    fun create(data: RepositoryData) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(convert2Entity(data))
        realm.commitTransaction()
    }

    fun find(userName: String): Int {
        val realm = Realm.getDefaultInstance()
        val list = realm.where(RepositoryEntity::class.java).equalTo("userName", userName).findAll()
        return list.count()
    }

    fun get(userName: String): List<RepositoryData> {
        val realm = Realm.getDefaultInstance()
        val list = realm.where(RepositoryEntity::class.java).equalTo("userName", userName).findAll()

        // Realmオブジェクトを別スレッドに受け渡して参照すると落ちるので、別の部ジェクトを作成
        val result= mutableListOf<RepositoryData>()
        list.forEach { result.add(convert2Data(it)) }
        return result
    }

    fun convert2Data(data: RepositoryEntity): RepositoryData {
        return RepositoryData(
                data.userName,
                data.name,
                data.url,
                data.readmeUrl,
                data.id)
    }

    fun convert2Entity(data: RepositoryData): RepositoryEntity {
        val entity = RepositoryEntity()
        entity.readmeUrl = data.readmeUrl
        entity.id = data.id
        entity.name = data.name
        entity.url = data.url
        entity.userName = data.userName
        return entity
    }


    fun deleteAll() {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(RepositoryEntity::class.java).findAll()
        realm.executeTransaction { result.deleteAllFromRealm() }
    }
}
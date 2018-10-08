package com.matsunaopen.repositoryviewer

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by DevUser on 2018/10/08.
 */
class RepositoryViewerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }

    override fun onTerminate() {
        super.onTerminate()
        Realm.getDefaultInstance().close()
    }
}
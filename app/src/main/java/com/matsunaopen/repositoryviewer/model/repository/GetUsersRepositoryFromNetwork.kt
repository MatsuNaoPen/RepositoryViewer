package com.matsunaopen.repositoryviewer.model.repository

import android.util.Log
import com.matsunaopen.repositoryviewer.RepositoryBehavior
import com.matsunaopen.repositoryviewer.data.RepositoryData
import com.matsunaopen.repositoryviewer.db.RepositoryDao
import com.matsunaopen.repositoryviewer.model.GetRepositoryService
import com.matsunaopen.repositoryviewer.model.response.ResponseGetRepos
import com.matsunaopen.repositoryviewer.view.RepositoryActivity
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Subscriber
import rx.schedulers.Schedulers

/**
 * Created by DevUser on 2018/10/06.
 */
class GetUsersRepositoryFromNetwork : IGetUsersRepository {
    override fun getUsersRepository(userName: String,
                                    behavior: RepositoryBehavior,
                                    callback: RepositoryActivity.RepositoryUpdateCallback) {
        client().schedule(userName)
                .subscribeOn(Schedulers.newThread())
                .subscribe(object : Subscriber<List<ResponseGetRepos>>() {
                    override fun onCompleted() {
                        // NOP
                    }

                    override fun onNext(r: List<ResponseGetRepos>?) {
                        val result = convert(userName, r.orEmpty())
                        callback.updateRepository(userName, result)
                        save(result)
                        Log.d("test", result.toString())
                    }

                    override fun onError(e: Throwable?) {
                        val errorText = e?.message ?: "unknown"
                        Log.d("test", "error:" + errorText)
                        // 失敗した場合はDBから取得しに行く
                        GetUsersRepositoryFromLocal().getUsersRepository(userName, behavior, callback)
                    }
                })
    }

    fun convert(userName: String, result: List<ResponseGetRepos>): List<RepositoryData> {
        val list = mutableListOf<RepositoryData>()
        result.forEach {
            val data = RepositoryData(
                    userName,
                    it.name,
                    it.html_url,
                    getReadmeUrl(it.html_url),
                    it.id)
            list.add(data)
        }
        return list
    }

    private fun getReadmeUrl(url: String): String {
        return if (url.contains("https://github.com")) {
            url + "/blob/master/README.md"
        } else {
            url
        }
    }

    private fun client(): GetRepositoryService {
        val moshi = Moshi.Builder().build()
        val okClient = OkHttpClient.Builder()
        val builder = Retrofit.Builder()
                .client(okClient.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl("https://api.github.com")
                .build()

        return builder.create(GetRepositoryService::class.java)
    }

    private fun save(data: List<RepositoryData>) {
        val dao = RepositoryDao()
        dao.create(data)
    }
}
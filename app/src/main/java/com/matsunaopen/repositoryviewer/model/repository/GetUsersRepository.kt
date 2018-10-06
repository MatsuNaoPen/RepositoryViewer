package com.matsunaopen.repositoryviewer.model.repository

import android.util.Log
import com.matsunaopen.repositoryviewer.data.RepositoryData
import com.matsunaopen.repositoryviewer.data.RepositoryDataList
import com.matsunaopen.repositoryviewer.model.GetRepositoryService
import com.matsunaopen.repositoryviewer.model.response.ResponseGetRepos
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observer
import rx.Subscriber
import rx.schedulers.Schedulers

/**
 * Created by DevUser on 2018/10/06.
 */
class GetUsersRepository {
    fun getUsersRepository(userName: String, observable: Observer<RepositoryDataList>) {
        client().schedule(userName)
                .subscribeOn(Schedulers.newThread())
                .subscribe(object : Subscriber<List<ResponseGetRepos>>() {
                    override fun onCompleted() {
                        // NOP

                    }

                    override fun onNext(r: List<ResponseGetRepos>?) {
                        val result = convert(r.orEmpty())
                        observable.onNext(result)
                        Log.d("test", result.toString())
                    }

                    override fun onError(e: Throwable?) {
                        val errorText = e?.message ?: "unknown"

                        Log.d("test", "error:" + errorText)
                    }
                })
    }

    fun convert(result: List<ResponseGetRepos>): RepositoryDataList =
            RepositoryDataList(result.map { RepositoryData(it.name, it.html_url) })

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
}
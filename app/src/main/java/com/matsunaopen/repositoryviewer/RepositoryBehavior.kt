package com.matsunaopen.repositoryviewer

/**
 * Created by DevUser on 2018/12/09.
 */
enum class RepositoryBehavior {
    MOCK,
    NETWORK,
    LOCAL;

    companion object {
        fun getBehavior(isMock: Boolean, isNetworkConnected: Boolean): RepositoryBehavior {
            return if (isMock) {
                RepositoryBehavior.MOCK
            } else {
                if (isNetworkConnected) {
                    RepositoryBehavior.NETWORK
                } else {
                    RepositoryBehavior.LOCAL
                }
            }
        }
    }
}
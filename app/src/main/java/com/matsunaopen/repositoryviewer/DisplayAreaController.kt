package com.matsunaopen.repositoryviewer

import com.airbnb.epoxy.Typed2EpoxyController
import com.matsunaopen.repositoryviewer.data.RepositoryData
import com.matsunaopen.repositoryviewer.view.RepositoryActivity

/**
 * Created by DevUser on 2018/10/06.
 */
class DisplayAreaController(
        private val callback: RepositoryActivity.RepositoryUpdateCallback)
    : Typed2EpoxyController<String?, List<RepositoryData>>() {

    override fun buildModels(userName: String?, data: List<RepositoryData>) {
        if (data.isEmpty()) {
            LayoutEmptyDataItemBindingModel_()
                    .id(0)
                    .addTo(this)
        } else {
            LayoutUserItemBindingModel_()
                    .id(0)
                    .userName(userName)
                    .addTo(this)

            data.forEach {
                LayoutRepositoryItemBindingModel_()
                        .data(it)
                        .id(it.id)
                        .callback(callback)
                        .addTo(this)
            }
        }
    }


}
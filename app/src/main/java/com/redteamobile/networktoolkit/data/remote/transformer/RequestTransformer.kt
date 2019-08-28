package com.redteamobile.networktoolkit.data.remote.transformer

import com.redteamobile.ferrari.net.exception.RequestException
import com.redteamobile.networktoolkit.data.remote.service.model.response.BasicResponse
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers

class RequestTransformer<T : BasicResponse> : ObservableTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(Schedulers.io())
            .doOnNext {
                if (it is BasicResponse && !it.success) {
                    throw RequestException(it.code, it.msg)
                }
            }
    }

}

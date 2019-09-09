package app.saltforest.samplearchitecture.infra.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.channelFlow

@ExperimentalCoroutinesApi
fun <T> LiveData<T>.asFlow() = channelFlow {
    offer(value)
    val observer = Observer<T> { t -> offer(t) }
    observeForever(observer)
    invokeOnClose { removeObserver(observer) }
}

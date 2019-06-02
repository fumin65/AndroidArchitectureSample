package app.saltforest.architecturesample.frontend.ui.memo

import androidx.lifecycle.ViewModelProviders
import app.saltforest.architecturesample.frontend.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MemoListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MemoViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)
            .get(MemoViewModel::class.java)
    }

}
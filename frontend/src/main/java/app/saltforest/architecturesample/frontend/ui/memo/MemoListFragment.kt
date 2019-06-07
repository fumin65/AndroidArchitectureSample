package app.saltforest.architecturesample.frontend.ui.memo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import app.saltforest.architecturesample.R
import app.saltforest.architecturesample.frontend.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_memo_list.*
import javax.inject.Inject

class MemoListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: MemoListAdapter

    private val viewModel: MemoViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)
            .get(MemoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_memo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        memoList.adapter = adapter
        memoList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter.onMemoSelected = viewModel::select
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel.memos.observe(this, Observer { adapter.memos = it })
    }

}
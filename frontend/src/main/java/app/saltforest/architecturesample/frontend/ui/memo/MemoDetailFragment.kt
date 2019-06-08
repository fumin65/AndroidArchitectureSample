package app.saltforest.architecturesample.frontend.ui.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import app.saltforest.architecturesample.R
import app.saltforest.architecturesample.databinding.FragmentMemoDetailBinding
import app.saltforest.architecturesample.frontend.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MemoDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MemoViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory).get(MemoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMemoDetailBinding>(
            inflater,
            R.layout.fragment_memo_detail,
            container,
            false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        viewModel.save()
    }
}
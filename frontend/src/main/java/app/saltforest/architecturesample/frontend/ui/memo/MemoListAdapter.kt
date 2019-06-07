package app.saltforest.architecturesample.frontend.ui.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.saltforest.architecturesample.R
import app.saltforest.architecturesample.databinding.CellMemoBinding
import app.saltforest.architecturesample.frontend.di.scope.FragmentScope
import javax.inject.Inject

@FragmentScope
class MemoListAdapter @Inject constructor() : RecyclerView.Adapter<MemoListAdapter.Holder>() {

    var memos: List<MemoRowData>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onMemoSelected: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<CellMemoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.cell_memo,
            parent,
            false
        )
        return Holder(binding)
    }

    override fun getItemCount(): Int = memos?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.apply {
            memo = memos?.get(position)
            memoCell.setOnClickListener { onMemoSelected?.invoke(position) }
        }
    }

    class Holder(val binding: CellMemoBinding) : RecyclerView.ViewHolder(binding.root)

}
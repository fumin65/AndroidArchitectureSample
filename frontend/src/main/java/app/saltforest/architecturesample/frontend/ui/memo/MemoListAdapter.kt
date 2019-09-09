package app.saltforest.architecturesample.frontend.ui.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.saltforest.architecturesample.R
import app.saltforest.architecturesample.databinding.CellMemoBinding
import app.saltforest.architecturesample.frontend.di.scope.FragmentScope
import javax.inject.Inject

@FragmentScope
class MemoListAdapter @Inject constructor() : RecyclerView.Adapter<MemoListAdapter.Holder>() {

    var memos: List<MemoRowData>? = null
        set(value) {
            if (value == null) {
                field = null
                notifyDataSetChanged()
                return
            }

            field?.also {
                val result = DiffUtil.calculateDiff(DiffCallback(it, value))
                field = value
                result.dispatchUpdatesTo(this)
            } ?: run {
                field = value
                notifyItemRangeInserted(0, value.size)
            }
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
            memoCell.setOnClickListener {
                memos?.indexOf(memo)?.let { index -> onMemoSelected?.invoke(index) }
            }
        }
    }

    class Holder(val binding: CellMemoBinding) : RecyclerView.ViewHolder(binding.root)

    private class DiffCallback(
        private val old: List<MemoRowData>,
        private val new: List<MemoRowData>
    ) :
        DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].id == new[newItemPosition].id
        }

        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }
    }

}

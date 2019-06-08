package app.saltforest.architecturesample.frontend.ui.memo

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.saltforest.architecturesample.R
import app.saltforest.architecturesample.app.data.Memo
import app.saltforest.architecturesample.app.usecase.CreateMemoUseCase
import app.saltforest.architecturesample.app.usecase.FetchMemoUseCase
import app.saltforest.architecturesample.app.usecase.FetchMemosUseCase
import app.saltforest.architecturesample.app.usecase.UpdateMemoUseCase
import app.saltforest.architecturesample.frontend.di.scope.ActivityScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class MemoViewModel @Inject constructor(
    private val context: Context,
    private val memoTranslator: MemoTranslator,
    private val fetchMemoUseCase: FetchMemoUseCase,
    private val createMemoUseCase: CreateMemoUseCase,
    private val updateMemoUseCase: UpdateMemoUseCase,
    private val fetchMemosUseCase: FetchMemosUseCase
) : ViewModel() {

    private val _selectedMemo = MutableLiveData<Memo>()
    val selectedMemo: LiveData<Memo?> = _selectedMemo

    val title = ObservableField<String>("")
    val content = ObservableField<String>("")

    private val _memos = MutableLiveData<List<MemoRowData>>()
    val memos: LiveData<List<MemoRowData>> = _memos

    init {
        loadMemos()
    }

    fun select(pos: Int) {
        viewModelScope.launch {
            memos.value?.get(pos)?.let {
                val memo = fetchMemoUseCase.handle(it.id)
                _selectedMemo.value = memo
                title.set(memo.title)
                content.set(memo.content)
            }
        }
    }

    fun new() {
        _selectedMemo.value = null
        title.set("")
        content.set("")
    }

    fun save() = viewModelScope.launch {

        val newTitle = if (title.get().isNullOrEmpty()) {
            context.getString(R.string.default_title)
        } else {
            title.get()!!
        }
        val newContent = content.get() ?: ""
        val selected = _selectedMemo.value

        if (selected != null) {
            updateMemoUseCase.handle(selected.id, newTitle, newContent)
        } else {
            createMemoUseCase.handle(newTitle, newContent)
        }

        loadMemos()
    }

    private fun loadMemos() {
        viewModelScope.launch {
            val memos = fetchMemosUseCase.handle()
                .sortedBy { it.lastUpdatedAt }
                .reversed()
                .map { memoTranslator.translate(it) }
            _memos.value = memos
        }
    }
}
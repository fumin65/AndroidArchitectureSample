package app.saltforest.architecturesample.frontend.ui.memo

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import app.saltforest.architecturesample.R
import app.saltforest.architecturesample.app.data.Memo
import app.saltforest.architecturesample.app.usecase.CreateMemoUseCase
import app.saltforest.architecturesample.app.usecase.FetchMemosUseCase
import app.saltforest.architecturesample.app.usecase.UpdateMemoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MemoViewModel @Inject constructor(
    private val context: Context,
    private val createMemoUseCase: CreateMemoUseCase,
    private val updateMemoUseCase: UpdateMemoUseCase,
    private val fetchMemosUseCase: FetchMemosUseCase
) : ViewModel() {

    val title = ObservableField<String>("")
    val content = ObservableField<String>("")

    val memos: LiveData<List<Memo>> = liveData {
        val memos = fetchMemosUseCase.handle()
        emit(memos)
    }

    private val _selectedMemo = MutableLiveData<Memo>()
    val selectedMemo: LiveData<Memo> = _selectedMemo

    fun select(memo: Memo) {
        _selectedMemo.value = memo
    }

    fun save() = viewModelScope.launch {

        val newTitle = title.get() ?: context.getString(R.string.default_title)
        val newContent = content.get() ?: ""
        val selected = _selectedMemo.value

        if (selected != null) {
            updateMemoUseCase.handle(selected.id, newTitle, newContent)
        } else {
            select(createMemoUseCase.handle(newTitle, newContent))
        }

    }


}
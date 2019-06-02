package app.saltforest.architecturesample.app.usecase

import app.saltforest.architecturesample.app.translator.MemoTranslator
import app.saltforest.architecturesample.domain.model.memo.MemoId
import app.saltforest.architecturesample.domain.model.memo.MemoRepository
import javax.inject.Inject

class UpdateMemoUseCase @Inject constructor(
    private val translator: MemoTranslator,
    private val repository: MemoRepository
) {

    suspend fun handle(targetId: String, title: String, content: String) {
        val memoId = MemoId(targetId)
        val targetMemo = repository.memoFor(memoId)
        targetMemo.changeTitleAndContent(title, content)
        repository.save(targetMemo)
    }

}
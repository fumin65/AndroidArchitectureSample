package app.saltforest.architecturesample.app.usecase

import app.saltforest.architecturesample.domain.model.memo.MemoId
import app.saltforest.architecturesample.domain.model.memo.MemoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateMemoUseCase @Inject constructor(
    private val repository: MemoRepository
) {

    suspend fun handle(targetId: String, title: String, content: String) {
        val memoId = MemoId(targetId)
        val targetMemo = repository.memoFor(memoId)
        targetMemo.changeTitleAndContent(title, content)
        repository.save(targetMemo)
    }

}
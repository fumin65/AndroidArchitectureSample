package app.saltforest.architecturesample.app.usecase

import app.saltforest.architecturesample.app.data.Memo
import app.saltforest.architecturesample.app.translator.MemoTranslator
import app.saltforest.architecturesample.domain.model.memo.MemoId
import app.saltforest.architecturesample.domain.model.memo.MemoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchMemoUseCase @Inject constructor(
    private val repository: MemoRepository,
    private val translator: MemoTranslator
) {

    suspend fun handle(id: String): Memo {
        return repository.memoFor(MemoId(id)).let { translator.translate(it) }
    }

}
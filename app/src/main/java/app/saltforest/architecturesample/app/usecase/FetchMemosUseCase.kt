package app.saltforest.architecturesample.app.usecase

import app.saltforest.architecturesample.app.data.Memo
import app.saltforest.architecturesample.app.translator.MemoTranslator
import app.saltforest.architecturesample.domain.model.memo.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchMemosUseCase @Inject constructor(
    private val translator: MemoTranslator,
    private val repository: MemoRepository
) {

    suspend fun handle(): List<Memo> {
        return withContext(Dispatchers.IO) { repository.memos() }
            .map { translator.translate(it) }
    }

}
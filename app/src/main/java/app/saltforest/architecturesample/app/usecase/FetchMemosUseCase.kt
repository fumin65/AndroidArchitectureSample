package app.saltforest.architecturesample.app.usecase

import app.saltforest.architecturesample.domain.model.memo.Memo
import app.saltforest.architecturesample.domain.model.memo.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchMemosUseCase @Inject constructor(
    private val repository: MemoRepository
) {

    suspend fun handle(): List<Memo> {
        return withContext(Dispatchers.IO) { repository.memos() }
    }
}
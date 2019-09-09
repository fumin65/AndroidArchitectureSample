package app.saltforest.architecturesample.domain.model.memo

import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    fun newId(): MemoId
    suspend fun memoFor(memoId: MemoId): Memo
    suspend fun save(memo: Memo)
    suspend fun memos(): Flow<List<Memo>>
}

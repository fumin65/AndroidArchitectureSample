package app.saltforest.architecturesample.domain.model.memo

interface MemoRepository {
    fun newId(): MemoId
    suspend fun memoFor(memoId: MemoId): Memo
    suspend fun save(memo: Memo)
    suspend fun memos(): List<Memo>
}
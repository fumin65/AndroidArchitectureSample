package app.saltforest.samplearchitecture.infra.memo

import app.saltforest.architecturesample.domain.model.memo.Memo
import app.saltforest.architecturesample.domain.model.memo.MemoId
import app.saltforest.architecturesample.domain.model.memo.MemoRepository
import app.saltforest.samplearchitecture.infra.persistence.dao.MemoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class RoomMemoRepository constructor(
    private val dao: MemoDao,
    private val translator: MemoTranslator
) : MemoRepository {

    override fun newId(): MemoId = MemoId(UUID.randomUUID().toString())

    override suspend fun save(memo: Memo) = dao.save(translator.translate(memo))

    override suspend fun memoFor(memoId: MemoId): Memo {
        return dao.memo(memoId.value).let { translator.translate(it) }
    }

    override suspend fun memos(): Flow<List<Memo>> {
        return dao.allMemos().map { memos -> memos.map { memo -> translator.translate(memo) } }
    }

}

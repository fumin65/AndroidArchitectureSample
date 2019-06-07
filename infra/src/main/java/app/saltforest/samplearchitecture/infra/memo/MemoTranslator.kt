package app.saltforest.samplearchitecture.infra.memo

import app.saltforest.architecturesample.domain.model.memo.Memo
import app.saltforest.architecturesample.domain.model.memo.MemoId
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoTranslator @Inject constructor() {

    fun translate(memo: Memo): app.saltforest.samplearchitecture.infra.persistence.entity.Memo {
        return app.saltforest.samplearchitecture.infra.persistence.entity.Memo(
            memo.id.value,
            memo.title,
            memo.content,
            memo.createdAt.time,
            memo.lastUpdatedAt.time
        )
    }

    fun translate(memo: app.saltforest.samplearchitecture.infra.persistence.entity.Memo): Memo {
        return Memo(
            MemoId(memo.id),
            memo.title,
            memo.content,
            Date(memo.createdAt),
            Date(memo.updatedAt)
        )
    }

}
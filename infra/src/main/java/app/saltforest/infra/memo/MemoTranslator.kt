package app.saltforest.infra.memo

import app.saltforest.architecturesample.domain.model.memo.Memo
import app.saltforest.architecturesample.domain.model.memo.MemoId
import java.util.*
import javax.inject.Singleton

@Singleton
class MemoTranslator {

    fun translate(memo: Memo): app.saltforest.infra.persistence.entity.Memo {
        return app.saltforest.infra.persistence.entity.Memo(
            memo.id.value,
            memo.title,
            memo.content,
            memo.createdAt.time,
            memo.lastUpdatedAt.time
        )
    }

    fun translate(memo: app.saltforest.infra.persistence.entity.Memo): Memo {
        return Memo(
            MemoId(memo.id),
            memo.title,
            memo.content,
            Date(memo.createdAt),
            Date(memo.updatedAt)
        )
    }

}
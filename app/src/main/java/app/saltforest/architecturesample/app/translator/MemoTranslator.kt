package app.saltforest.architecturesample.app.translator

import app.saltforest.architecturesample.domain.model.memo.Memo
import javax.inject.Singleton

@Singleton
class MemoTranslator {

    fun translate(memo: Memo): app.saltforest.architecturesample.app.data.Memo {
        return app.saltforest.architecturesample.app.data.Memo(
            memo.id.value,
            memo.title,
            memo.content,
            memo.createdAt,
            memo.lastUpdatedAt
        )
    }

}
package app.saltforest.architecturesample.domain.model.memo

import java.util.*


class Memo(
    val id: MemoId,
    title: String,
    var content: String,
    val createdAt: Date,
    lastUpdatedAt: Date = createdAt
) {

    companion object {
        private const val MAX_TITLE_LENGTH = 100
    }

    var title: String = title
        private set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("title must not be empty")
            } else if (value.length > MAX_TITLE_LENGTH) {
                throw IllegalArgumentException("title must be under than ${MAX_TITLE_LENGTH + 1}")
            }
            field = value
        }

    var lastUpdatedAt: Date = lastUpdatedAt
        private set

    fun changeTitleAndContent(title: String, content: String) {
        this.title = title
        this.content = content
        this.lastUpdatedAt = Date()
    }

}
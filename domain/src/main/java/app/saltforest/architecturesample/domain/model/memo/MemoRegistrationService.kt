package app.saltforest.architecturesample.domain.model.memo

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoRegistrationService @Inject constructor(
    private val repository: MemoRepository
) {

    suspend fun registerBy(title: String, content: String): Memo {
        val memo = Memo(
            repository.newId(),
            title,
            content,
            Date()
        )
        repository.save(memo)
        return memo
    }

}
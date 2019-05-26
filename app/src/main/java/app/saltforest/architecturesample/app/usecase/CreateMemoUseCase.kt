package app.saltforest.architecturesample.app.usecase

import app.saltforest.architecturesample.domain.model.memo.Memo
import app.saltforest.architecturesample.domain.model.memo.MemoRegistrationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateMemoUseCase @Inject constructor(
    private val memoRegistrationService: MemoRegistrationService
) {

    suspend fun handle(title: String, content: String): Memo {
        // TODO: トランザクションをここで明示的に管理するようにする
        return withContext(Dispatchers.IO) {
            memoRegistrationService.registerBy(title, content)
        }
    }

}
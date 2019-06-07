package app.saltforest.architecturesample.frontend.ui.memo

import android.annotation.SuppressLint
import app.saltforest.architecturesample.app.data.Memo
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoTranslator @Inject constructor() {

    companion object {
        @SuppressLint("SimpleDateFormat")
        private val DATE_FORMATTER = SimpleDateFormat("yyyy/MM/dd HH:mm")
    }

    fun translate(memo: Memo): MemoRowData {
        return MemoRowData(memo.id, memo.title, DATE_FORMATTER.format(memo.lastUpdatedAt))
    }

}
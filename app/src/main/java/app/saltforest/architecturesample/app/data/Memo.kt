package app.saltforest.architecturesample.app.data

import java.util.*

data class Memo(
    val id: String,
    var title: String,
    var content: String,
    val createdAt: Date,
    val lastUpdatedAt: Date
)
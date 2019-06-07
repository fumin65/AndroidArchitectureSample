package app.saltforest.samplearchitecture.infra.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long
)
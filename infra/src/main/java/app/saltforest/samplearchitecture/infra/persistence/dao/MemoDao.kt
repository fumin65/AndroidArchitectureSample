package app.saltforest.samplearchitecture.infra.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.saltforest.samplearchitecture.infra.persistence.entity.Memo

@Dao
interface MemoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(memo: Memo)

    @Query("SELECT * FROM Memo WHERE id = :id")
    suspend fun memo(id: String): Memo

    @Query("SELECT * FROM Memo")
    suspend fun allMemos(): List<Memo>

}
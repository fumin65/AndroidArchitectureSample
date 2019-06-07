package app.saltforest.samplearchitecture.infra.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import app.saltforest.samplearchitecture.infra.persistence.dao.MemoDao
import app.saltforest.samplearchitecture.infra.persistence.entity.Memo

@Database(entities = [Memo::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun memoDao(): MemoDao

}
package app.saltforest.infra.di

import android.content.Context
import androidx.room.Room
import app.saltforest.architecturesample.domain.model.memo.MemoRepository
import app.saltforest.infra.memo.MemoTranslator
import app.saltforest.infra.memo.RoomMemoRepository
import app.saltforest.infra.persistence.Database
import app.saltforest.infra.persistence.dao.MemoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InfraModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "app.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideMemoDao(db: Database): MemoDao = db.memoDao()

    @Provides
    @Singleton
    fun provideMemoRepository(dao: MemoDao, translator: MemoTranslator): MemoRepository {
        return RoomMemoRepository(dao, translator)
    }

}
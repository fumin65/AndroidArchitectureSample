package app.saltforest.samplearchitecture.infra.di

import android.content.Context
import androidx.room.Room
import app.saltforest.architecturesample.domain.model.memo.MemoRepository
import app.saltforest.samplearchitecture.infra.memo.MemoTranslator
import app.saltforest.samplearchitecture.infra.memo.RoomMemoRepository
import app.saltforest.samplearchitecture.infra.persistence.Database
import app.saltforest.samplearchitecture.infra.persistence.dao.MemoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InfraModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "app.db").build()
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
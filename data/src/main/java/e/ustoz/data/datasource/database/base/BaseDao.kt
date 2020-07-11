package e.ustoz.data.datasource.database.base

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Suppress("MemberVisibilityCanBePrivate", "unused")
internal abstract class BaseDao<T : Any> {

    @Delete
    abstract fun delete(collection: Collection<T>)

    @Delete
    abstract fun delete(value: T)

    @Deprecated("")
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(value: T): Long

    @Deprecated("")
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(collection: Collection<T>): LongArray

//    @Deprecated("")
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(value: T): Long

//    @Deprecated("")
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(collection: Collection<T>)

    @Suppress("SpellCheckingInspection", "DEPRECATION")
    @Transaction
    open fun upsert(value: T): Long {
        val insertResult: Long = insert(value)
        return if (insertResult == -1L) update(value)
        else insertResult
    }

    @Suppress("SpellCheckingInspection", "DEPRECATION")
    @Transaction
    open fun upsert(collection: Collection<T>) {
        val list: List<T> = collection.toList()
        val insertResults: LongArray = insert(list)
        val updatedList: MutableList<T> = mutableListOf()

        insertResults.indices.forEach {
            insertResults[it].let { result -> if (result == -1L) updatedList.add(list[it]) }
        }

        if (updatedList.isNotEmpty()) update(updatedList)
    }

    fun <T : Any, Dao : BaseDao<*>> Dao.asFlow(action: Dao. () -> T): Flow<T> =
        flow { emit(action.invoke(this@asFlow)) }
}
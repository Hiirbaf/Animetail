package tachiyomi.data.handlers.anime

import androidx.paging.PagingSource
import app.cash.sqldelight.ExecutableQuery
import app.cash.sqldelight.Query
import kotlinx.coroutines.flow.Flow
import tachiyomi.mi.data.AnimeDatabase

interface AnimeDatabaseHandler {

    suspend fun <T> await(inTransaction: Boolean = false, block: suspend AnimeDatabase.() -> T): T

    suspend fun <T : Any> awaitList(
        inTransaction: Boolean = false,
        block: suspend AnimeDatabase.() -> Query<T>,
    ): List<T>

    suspend fun <T : Any> awaitOne(
        inTransaction: Boolean = false,
        block: suspend AnimeDatabase.() -> Query<T>,
    ): T

    suspend fun <T : Any> awaitOneExecutable(
        inTransaction: Boolean = false,
        block: suspend AnimeDatabase.() -> ExecutableQuery<T>,
    ): T

    // SY -->
    suspend fun <T : Any> awaitListExecutable(
        inTransaction: Boolean = false,
        block: suspend AnimeDatabase.() -> ExecutableQuery<T>,
    ): List<T>
    // SY <--

    suspend fun <T : Any> awaitOneOrNull(
        inTransaction: Boolean = false,
        block: suspend AnimeDatabase.() -> Query<T>,
    ): T?

    suspend fun <T : Any> awaitOneOrNullExecutable(
        inTransaction: Boolean = false,
        block: suspend AnimeDatabase.() -> ExecutableQuery<T>,
    ): T?

    fun <T : Any> subscribeToList(block: AnimeDatabase.() -> Query<T>): Flow<List<T>>

    fun <T : Any> subscribeToOne(block: AnimeDatabase.() -> Query<T>): Flow<T>

    fun <T : Any> subscribeToOneOrNull(block: AnimeDatabase.() -> Query<T>): Flow<T?>

    fun <T : Any> subscribeToPagingSource(
        countQuery: AnimeDatabase.() -> Query<Long>,
        queryProvider: AnimeDatabase.(Long, Long) -> Query<T>,
    ): PagingSource<Long, T>
}

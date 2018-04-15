package me.w2x.demo.common

/**
 * Created by Charles Chen on 05/03/2018.
 */
data class QueryResult<out T>(val totalCount: Long, val data: Iterable<T>) {
    companion object {
        val EMPTY = QueryResult<Any>(0, emptyList())
    }
}
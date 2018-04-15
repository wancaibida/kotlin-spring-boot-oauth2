package me.w2x.demo.dao

import me.w2x.demo.entity.User
import me.w2x.demo.filter.UserFilter
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

/**
 * Created by Charles Chen on 27/02/2018.
 */
interface UserRepositoryCustom {
    fun findAll(userFilter: UserFilter?, pageable: Pageable?): Iterable<User>

    fun count(userFilter: UserFilter?): Long
}

@Repository
class UserRepositoryImpl(val entityManager: EntityManager) : UserRepositoryCustom {
    override fun findAll(userFilter: UserFilter?, pageable: Pageable?): Iterable<User> {
        val sql = StringBuilder("FROM User u")
        val (condition, params) = getConditionAndParamter(userFilter)

        sql.append(condition)

        if (pageable?.sort != null) {
            val sort = pageable.sort

            sql.append(" ORDER BY ")
            sql.append(sort.joinToString(",") { "${it.property} ${it.direction.name}" })
        }


        val query = entityManager.createQuery(sql.toString(), User::class.java)

        query.apply {
            for ((key, value) in params) {
                setParameter(key, value)
            }

            if (pageable != null) {
                firstResult = pageable.pageNumber * pageable.pageSize
                maxResults = pageable.pageSize
            }
        }

        return query.resultList
    }

    override fun count(userFilter: UserFilter?): Long {
        val sql = StringBuilder("SELECT COUNT(1) FROM User u")
        val (condition, params) = getConditionAndParamter(userFilter)

        sql.append(condition)
        val query = entityManager.createQuery(sql.toString(), Long::class.java)
        for ((key, value) in params) {
            query.setParameter(key, value)
        }

        return query.singleResult
    }

    fun getConditionAndParamter(userFilter: UserFilter?): Pair<String, Map<String, Any?>> {
        if (userFilter == null) {
            return Pair("", emptyMap())
        }

        val condition = StringBuilder(" WHERE 1=1 ")
        val params = mutableMapOf<String, Any>()

        if (!userFilter.username.isNullOrBlank()) {
            condition.append("AND u.username LIKE :username")
            params["username"] = "%${userFilter.username!!}%"
        }

        return Pair(condition.toString(), params)
    }
}

interface UserRepository : CrudRepository<User, Long>, UserRepositoryCustom
package me.w2x.demo.resolver

import me.w2x.demo.common.Pageable
import me.w2x.demo.common.toPageRequest
import me.w2x.demo.dao.UserRepository
import me.w2x.demo.entity.User
import me.w2x.demo.filter.UserFilter
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

/**
 * Created by Charles Chen on 28/02/2018.
 * Example:
 *
 * {
 *      findAllUsers(filter: {username: "a"}, page: {page: 0, sortName: "id", sortOrder: "asc"}) {
 *          username
 *      }
 * }
 */
@Component
class Query(val userRepository: UserRepository) : GraphQLQueryResolver {

    fun findAllUsers(pageable: Pageable?, userFilter: UserFilter?): Iterable<User> {
        return userRepository.findAll(userFilter, pageable?.toPageRequest())
    }

    fun countUsers(userFilter: UserFilter?): Long {
        return userRepository.count(userFilter)
    }
}
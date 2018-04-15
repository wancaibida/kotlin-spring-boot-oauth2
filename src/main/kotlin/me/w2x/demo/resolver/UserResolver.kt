package me.w2x.demo.resolver

import me.w2x.demo.dao.UserRepository
import me.w2x.demo.entity.User
import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.stereotype.Component
import javax.persistence.EntityManager

/**
 * Created by Charles Chen on 28/02/2018.
 */
@Component
class UserResolver(val userRepository: UserRepository, val entityManager: EntityManager) : GraphQLResolver<User> {

    fun getUser(): User? {
        return userRepository.findOne(1)
    }
}
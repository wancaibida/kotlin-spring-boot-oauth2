package me.w2x.demo.service

import me.w2x.demo.command.UserCommand
import me.w2x.demo.common.QueryResult
import me.w2x.demo.dao.RoleRepository
import me.w2x.demo.dao.UserRepository
import me.w2x.demo.entity.Role
import me.w2x.demo.entity.User
import me.w2x.demo.filter.UserFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Created by Charles Chen on 27/02/2018.
 */
@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun create(userCommand: UserCommand): User {
        val user = User().apply {
            username = userCommand.username
            password = passwordEncoder.encode(userCommand.password)
            val role = roleRepository.findOneByAuthority(userCommand.role!!)
            roles = mutableListOf<Role>(role)
        }

        return userRepository.save(user)
    }

    fun queryUsers(userFilter: UserFilter, pageable: Pageable): QueryResult<User> {
        val count = userRepository.count(userFilter)
        if (count == 0L) {
            return QueryResult(0L, emptyList())
        }

        val data = userRepository.findAll(userFilter, pageable)

        return QueryResult(count, data)
    }
}
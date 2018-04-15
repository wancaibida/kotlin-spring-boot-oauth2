package me.w2x.demo.dao

import me.w2x.demo.entity.Role
import org.springframework.data.repository.Repository

/**
 * Created by Charles Chen on 27/02/2018.
 */
interface RoleRepository : Repository<Role, Long> {

    fun findOneByAuthority(authority: String): Role
}
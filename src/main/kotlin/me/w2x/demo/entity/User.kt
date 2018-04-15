package me.w2x.demo.entity

import javax.persistence.*

/**
 * Created by Charles Chen on 24/02/2018.
 */
@Entity
@Table(name = "users")
class User(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var username: String? = null,
        var password: String? = null,
        var enabled: Boolean? = true,
        var accountNonExpired: Boolean? = true,
        var credentialsNonExpired: Boolean? = true,
        var accountNonLocked: Boolean? = true,

        @JoinTable(
                name = "users_roles",
                joinColumns = [JoinColumn(
                        name = "user_id",
                        referencedColumnName = "id"
                )],
                inverseJoinColumns = [(JoinColumn(
                        name = "role_id",
                        referencedColumnName = "id"
                ))]
        )
        @OneToMany(fetch = FetchType.EAGER)
        var roles: MutableList<Role>? = null
)
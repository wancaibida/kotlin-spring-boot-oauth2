package me.w2x.demo.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by Charles Chen on 24/02/2018.
 */
@Entity
@Table(name = "oauth_access_token")
class AccessToken(
        @Id
        var id: Long? = null,
        var tokenId: String? = null,
        val token: ByteArray? = null,
        @Column(unique = true)
        val authenticationId: String? = null,
        val username: String? = null,
        val clientId: String? = null,
        val authentication: ByteArray? = null,
        val refreshToken: String? = null
)
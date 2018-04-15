package me.w2x.demo.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by Charles Chen on 24/02/2018.
 */
@Entity
@Table(name = "oauth_refresh_token")
class RefreshToken(
        @Id
        var id: Long? = null,
        var tokenId: String? = null,
        var token: ByteArray? = null,
        var authentication: ByteArray? = null
)
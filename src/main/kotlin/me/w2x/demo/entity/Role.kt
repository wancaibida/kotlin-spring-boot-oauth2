package me.w2x.demo.entity

import javax.persistence.Entity
import javax.persistence.Id

/**
 * Created by Charles Chen on 24/02/2018.
 */
@Entity
class Role(
        @Id
        var id: Long? = null,
        var authority: String? = null
)
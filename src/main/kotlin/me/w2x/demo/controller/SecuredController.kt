package me.w2x.demo.controller

import me.w2x.demo.enu.RoleTypes
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Charles Chen on 26/02/2018.
 */
@RestController
@RequestMapping("secured")
class SecuredController {

    @GetMapping("admin")
    @Secured(RoleTypes.Constants.ROLE_ADMIN)
    fun admin(): ResponseEntity<Any> {
        return ResponseEntity.ok("role" to RoleTypes.ROLE_ADMIN.authority)
    }
}
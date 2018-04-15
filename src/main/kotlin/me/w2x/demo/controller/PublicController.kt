package me.w2x.demo.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Charles Chen on 26/02/2018.
 */
@RestController
@RequestMapping("public")
class PublicController {

    @GetMapping("health/status")
    fun healthCheck(): ResponseEntity<Any?> {
        return ResponseEntity.ok(mapOf("success" to true))
    }
}
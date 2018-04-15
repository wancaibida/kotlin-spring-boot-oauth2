package me.w2x.demo.controller

import me.w2x.demo.command.UserCommand
import me.w2x.demo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Created by Charles Chen on 27/02/2018.
 */
@RestController
@RequestMapping("users")
class UserController(val userService: UserService) {

    @PostMapping
    fun create(@Valid @RequestBody userCommand: UserCommand): ResponseEntity<Any> {
        val user = userService.create(userCommand)
        return ResponseEntity(user, HttpStatus.CREATED)
    }
}
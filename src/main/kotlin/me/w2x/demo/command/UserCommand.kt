package me.w2x.demo.command

import javax.validation.constraints.NotBlank

/**
 * Created by Charles Chen on 27/02/2018.
 */
class UserCommand {

    @NotBlank
    var username: String? = null

    @NotBlank
    var password: String? = null

    @NotBlank
    var role: String? = null
}
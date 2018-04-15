package me.w2x.demo.enu

/**
 * Created by Charles Chen on 25/02/2018.
 */
enum class RoleTypes(val authority: String) {
    ROLE_ADMIN(Constants.ROLE_ADMIN);

    class Constants {
        companion object Constants{
            const val ROLE_ADMIN = "ROLE_ADMIN"
        }
    }
}
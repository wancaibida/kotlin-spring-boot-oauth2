package migration

import me.w2x.demo.enu.RoleTypes
import org.flywaydb.core.api.migration.spring.SpringJdbcMigration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Created by Charles Chen on 25/02/2018.
 */
class V2__Add_admin_account : SpringJdbcMigration {
    override fun migrate(jdbcTemplate: JdbcTemplate) {
        val password: String = BCryptPasswordEncoder().encode("qEWD2LDvE9MWrR")
        val username = "admin"
        val accountSql = """
INSERT INTO users (id, account_non_expired, account_non_locked, username, "password", credentials_non_expired, enabled)
VALUES(nextval('hibernate_sequence'),
       TRUE,
       TRUE,
       ?,
       ?,
       TRUE,
       TRUE)
        """.trimIndent()

        val roleSql = "INSERT INTO role (id, authority) VALUES (1 ,?)"
        jdbcTemplate.update(accountSql, username, password)
        jdbcTemplate.update(roleSql, RoleTypes.ROLE_ADMIN.authority)

        val id = jdbcTemplate.queryForObject("SELECT id FROM users WHERE username = ?", Long::class.java, username)
        jdbcTemplate.update("INSERT INTO users_roles (user_id, role_id) VALUES(?,1)", id)
    }
}
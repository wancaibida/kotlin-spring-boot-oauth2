package me.w2x.demo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import javax.sql.DataSource

/**
 * Created by Charles Chen on 24/02/2018.
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration : AuthorizationServerConfigurerAdapter() {

    @Qualifier("dataSource")
    @Autowired
    lateinit var dataSource: DataSource

    @Autowired
    lateinit var tokenStore: TokenStore

    @Autowired
    @Qualifier("authenticationManagerBean")
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userDetailsManager: UserDetailsManager


    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory().withClient("W2X")
                .resourceIds("test1234")
                .authorizedGrantTypes("authorization_code", "implicit", "refresh_token", "password")
                .authorities("ROLE_CLIENT")
                .scopes("read", "write")
                .secret("secret")
    }


    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.apply {
            tokenStore(this@AuthorizationServerConfiguration.tokenStore)
            authenticationManager(authenticationManager)
            userDetailsService(userDetailsManager)
            reuseRefreshTokens(false)
        }
    }

    @Bean
    fun tokenStore(): TokenStore {
        return JdbcTokenStore(dataSource)
    }

    @Bean
    fun userDetailsManager(): UserDetailsManager {
        val jdbcUserDetailsManager = JdbcUserDetailsManager()
        jdbcUserDetailsManager.dataSource = dataSource
        jdbcUserDetailsManager.apply {
            setAuthoritiesByUsernameQuery("""
                SELECT username,
                       authority
                FROM users
                LEFT JOIN users_roles ON users.id = users_roles.user_id
                LEFT JOIN ROLE ON users_roles.role_id= role.id
                WHERE username = ?
            """.trimIndent())
        }
        return jdbcUserDetailsManager
    }
}
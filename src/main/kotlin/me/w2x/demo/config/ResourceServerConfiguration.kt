package me.w2x.demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer

/**
 * Created by Charles Chen on 24/02/2018.
 */
@Configuration
@EnableResourceServer
class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.resourceId("test1234").stateless(true)
    }

    override fun configure(http: HttpSecurity) {
        http.anonymous()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().requestMatchers().anyRequest()
                .and().authorizeRequests()
                .antMatchers("/public/**").anonymous()
                .antMatchers("/graphql/**").anonymous()
                .antMatchers("/graphql").anonymous()
                .antMatchers("/graphiql").anonymous()
                .antMatchers("/graphiql/**").anonymous()
                .anyRequest().authenticated()
    }
}
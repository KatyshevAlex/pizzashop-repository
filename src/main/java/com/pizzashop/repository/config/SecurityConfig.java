package com.pizzashop.repository.config;


import com.pizzashop.repository.enity.security.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Autowired
    private CustomBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Value("${security.queries.customers}")
    private String customersQuery;

    @Value("${security.queries.roles}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(customersQuery)
                .authoritiesByUsernameQuery(rolesQuery);
    }

    //Step 2 - configure encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Step 3 - configure http requests by roles
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/dao/find-customer").hasAnyRole(RoleType.ROLE_APPLICATION.getThisAndHigherPriorities())
                .antMatchers(HttpMethod.PUT, "/dao/update-customer").hasAnyRole(RoleType.ROLE_APPLICATION.getThisAndHigherPriorities())
                .antMatchers(HttpMethod.PUT, "/dao/create-customer").hasAnyRole(RoleType.ROLE_APPLICATION.getThisAndHigherPriorities())
                .and()
                .formLogin()
                .defaultSuccessUrl("/logged-in",true)
                .failureUrl("/login-error")
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf().disable();
    }

}

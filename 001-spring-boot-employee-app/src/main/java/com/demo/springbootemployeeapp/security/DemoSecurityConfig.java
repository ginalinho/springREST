package com.demo.springbootemployeeapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers(HttpMethod.GET, "/employees/list").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/employees/showFormForAdd").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/employees/showFormForUpdate").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/employees/save").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/employees/delete").hasRole("ADMIN")
        );

        //use HTTP Basic authentication
        http.httpBasic();

        //disable Cross Site Request Forgery
        http.csrf().disable();

        return http.build();
    }
}
